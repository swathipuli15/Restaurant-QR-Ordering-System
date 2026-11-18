
import {useEffect,useState} from "react";
import API from "../services/api";
function Kitchen(){
    const[orders,setOrders]=useState([]);
    const[message,setMessage]=useState("Loading orders...");
    const[previousOrderCount,setPreviousOrderCount]=useState(0);
    const[filter,setFilter]=useState("ALL");
    const loadOrders=async()=>{
        try{
            const response =await API.get("/kitchen/orders");
            console.log("Kitchen Orders:",response.data);
            const data=response.data;
            if(Array.isArray(data)){
                if(data.length>previousOrderCount){
                    const audio=new Audio("/notification.mp3");
                    audio.play().catch(error=>console.log("Sound notification blocked:",error));
                }
                setPreviousOrderCount(data.length);
                
                setOrders(data);    
                setMessage(response.data.length===0?"NO order Available":"Order loaded Sucessfully");
            }else if(Array.isArray(data.content)){
                setOrders(data.content);
                setMessage(data.content.length===0?"No Orders Available":"Orders Loaded SucessFully");

            }else{
                setOrders([]);
                setMessage("Unexpected API response format");
            }
            // setOrders(response.data);
            // setMessage(response.data.length===0?"NO order Available":"Order loaded Sucessfully");
        }catch(error){
            console.error("Error loading orders:",error);
            setOrders([]);
            setMessage("Unable to load orders:" +error.message);
        }
    };
    useEffect(()=>{loadOrders();
      
        const interval=setInterval(()=>{loadOrders();},10000);
        return()=>clearInterval(interval);
    },[]);
    const updateStatus=async(orderID,status)=>{
        try{
            await API.put(`/kitchen/orders/${orderID}/status?status=${status}`);
            alert("Order status updated sucessfully");
            loadOrders();
        }catch(error){
            console.error("Status Update Error:",error);
            alert("Unable to update order status");
        }

        };
          const filteredOrders=orders.filter(order=>filter==="ALL" || order.orderStatus?.toUpperCase()===filter);
        return(<div style={{padding:"30px"}}>
            <h1>Kitchen Dashboard</h1>
            <p>{message}</p>
            <div style={{marginBottom:"20px"}}>
                <button onClick={()=>setFilter("ALL")}>All Orders</button>
                <button onClick={()=>setFilter("PENDING")}>Pending</button>
                <button onClick={()=>setFilter("PREPARING")}>Preparing</button>
                <button onClick={()=>setFilter("COMPLETED")}>Completed</button>
            </div>
            {filteredOrders.map(order=>(<div key={order.orderId} style={{border:"1px solid gray",padding:"20px",margin:"15px",borderRadius:"10px"}}>
                <h2>Order #{order.orderId}</h2>
                <p>Table Number:{order.tableSession?.restaurantTable?.tableNumber}</p>
                <p>Order Status:{order.orderStatus}</p>
                <p>Total Amount: ₹{order.totalAmount}</p>
                <h3>Ordered Food Items</h3>
                {order.orderItem?.map(item=>(<div key={item.itemId}>
                    <p>Food Name:{item.foodItem?.foodName}</p>
                    <p>Quantity:{item.quantity}</p>
                    <p>Price:₹{item.price}</p>
                    </div>
                        ))}
                <button onClick={()=>updateStatus(order.orderId,"PREPARING")}>preparing</button>
                <button onClick={()=>updateStatus(order.orderId,"COMPLETED")}>Completed</button>
                </div>))}
        </div>
        );
    
}
export default Kitchen;