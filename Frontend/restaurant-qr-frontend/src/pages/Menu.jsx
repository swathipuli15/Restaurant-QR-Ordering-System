import {useEffect,useState} from "react";
import {useParams,useNavigate} from "react-router-dom";
import API from "../services/api";
function Menu(){
    const{qrToken}=useParams();
    const[foods,setFoods]=useState([]);
    const[cart,setCart]=useState([]);
    const[sessionId,setSessionId]=useState(null);
    const[orderMessage,setOrderMessage]=useState("");
    const[message,setMessage]=useState("Loading food items...");
    const navigate=useNavigate();
    const[orderId,setOrderId]=useState(null);


    const placeOrder=async()=>{
        
        if(!sessionId){
            alert("Table session is not started yet");
            return;
        }
        if(cart.length===0){
            alert("Please add food items to your cart");
            return;
        }
        const orderData={
            sessionId:sessionId,
            items:cart.map(item=>({
                foodId:item.foodId,
                quantity:item.quantity
            }))
        };
        try{
            const response=await API.post("/orders",orderData);
            console.log("Order placed:" ,response.data);
            setOrderId(response.data.orderId);
            setOrderMessage("Order placed Sucessfully");
            setCart([]);
        }catch(error){
            console.error("Order Error:",error.reponse?.data||error);
            setOrderMessage("Unable to place order");
        }
    };

    useEffect(()=>{API.post(`/sessions/start/${qrToken}`).then(response=>{
   setSessionId(response.data.sessionId);
    console.log("Session Started:" ,response.data);
}).catch(error=>{console.error("Session Error",error);
});
    
        API.get("/foods").then(response=>{
        setFoods(response.data);
        setMessage("Food items loaded Sucessfully");

    }).catch(error=>{console.error("Food API Error:",error);
        setMessage("Unable to load food items:"+error.message);

    });
},[qrToken]);
const addToCart=(food)=>{setCart(previousCart=>{const existingItem=previousCart.find(item=>item.foodId===food.foodId);
    if(existingItem){
        return previousCart.map(item=>item.foodId===food.foodId?{...item,quantity:item.quantity+1}:item);
        // setCart(cart.map(item=>item=>item.foodId===food.foodId?{...item,quantity:item.quantity+1}:item));
    // }else{
    //     setCart([...cart,{...food,quantity:1}]);
    
    // };
}return [...previousCart,{...food,quantity:1}];
});
};
    const totalAmount=cart.reduce((total,item)=>total+item.price*item.quantity,0);


return(<div style={{padding:"30px"}}>
    <h1>Restaurant Menu</h1>
    <p>Table QR Token:{qrToken}</p>
    <h2>Available Food Items</h2>
    <p>{message}</p>
    <p>SessionId:{sessionId||"Start Session.."}</p>
    {foods.filter(food=>food.availability)
    .map(food=>(<div key={food.foodId}>
        <h3>{food.foodName}</h3>
        <img src={food.imgUrl}
alt={food.foodName} style={{width:"200px",height:"150px",objectFit:"cover",borderRadius:"10px",display:"block"}} />
    <p>{food.description}</p>
    <p>Price:₹{food.price}</p>
    <p>Category:{food.category?.categoryName}</p>
    <button onClick={()=>addToCart(food)}>AddToCart</button>
    </div>
))}

<hr />
<h2>Shoping Cart</h2>
{cart.length===0?(<p>Your cart is empty</p>):(cart.map(item=>(
    <div key={item.foodId}>
    <p>{item.foodName}-₹{item.price}*{item.quantity}</p>
        </div>
        ))
    )}
    <h3>Total Amount:₹{totalAmount}</h3>
    <button onClick={placeOrder}>Place Order</button>
    <p>{orderMessage}</p>
    {orderId &&(
        <div style={{border:"1px solid green",
            padding:"15px",
            marginTop:"15px",
            borderRadius:"8px"
        }}>
    <h3>Order Placed Sucessfully</h3>
    <p>YOur Order Id:{orderId}</p>
    <button onClick={()=>navigate("/track-order",{state:{orderId:orderId}
    })}>
        Track My Order
    </button>

</div>
    )}
    </div>
);

}
export default Menu;