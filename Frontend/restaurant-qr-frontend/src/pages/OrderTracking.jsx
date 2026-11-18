

// import {useState} from "react";

import API from "../services/api";
import {useState,useEffect,useCallback} from "react";
import {useLocation} from "react-router-dom";
function OrderTracking(){
    const location=useLocation();
    const[orderId,setOrderId]=useState(location.state?.orderId||"");
    const[order,setOrder]=useState(null);
    const[message,setMessage]=useState("");
    

    const loadOrder=useCallback(async()=>{if(!orderId)
        return ;
        try{
            const response=await API.get(`/orders/${orderId}`);
            setOrder(response.data);
            setMessage("Order details loaded sucessfully");
        }
        catch(error){
            setOrder(null);
            setMessage("Order Not Found");
        }
    },[orderId]);
    useEffect(()=>{
        if(!orderId) return;
        loadOrder();
        const interval=setInterval(()=>{
            loadOrder();
        },5000);
        return ()=>clearInterval(interval);

    },[orderId,loadOrder]);

    const trackOrder=async()=>{
        if(!orderId){setMessage("Please enter Order ID");
        return;
        }
        await loadOrder();
        // try{
        //     const response=await API.get(`/orders/${orderId}`);
        //     setOrder(response.data);
        //     setMessage("Order details loaded sucessfully");
        // }catch(error){
        //     console.error("Order Tracking Error:",error);
        //     console.error("Error Response:",error.response?.data);
        //     console.error("Status Code:",error.response?.status);
        //     setOrder(null);
        //     setMessage(error.response?`API Error:${error.response.status}`:"Unable to connect backend");
        // }
    };

    return(
        <div >
            <h1>Track Your Order</h1>
           
            <input type="number" placeholder="Enter Order Id" value={orderId} onChange={(e)=>setOrderId(e.target.value)}/>
            <button onClick={trackOrder}>Track Order</button>
            <p>{message}</p>
            {order &&(
                <div style={{marginTop:"20px"}}>
                <h3>Order #{order.orderId}</h3>
                <p>Status:{order.orderStatus}</p>
                <p>Total Amount:₹{order.totalAmount}</p>
                <h3>Order Progress</h3>
                <div style={{display:"flex",gap:"10px"}}>
                    {["PENDING","PREPARING","COMPLETED"].map((status,index)=>{
                        const statuses=["PENDING","PREPARING","COMPLETED"];
                    const currentIndex=statuses.indexOf(order.orderStatus?.toUpperCase());
                    return(
                        <div key={status} style={{padding:"10px" ,
                            background:index <=currentIndex ? "green":"lightgray",
                            color:index <=currentIndex ? "white":"black",
                            borderRadius:"5px"
                        }}
                    >
                {status}
                    </div>
                );
                })}
                   
                
            </div>
        </div>
            )}
              <h3>Ordered Food Items</h3>
            {order?.orderItem?.map((item)=>(
                <div key={item.itemId} style={{border:"1px solid gray",padding:"10px",margin:"10px 0"}}>
                <p>Food Name:{item.foodItem?.foodName}</p>
                <p>Quantity:{item.quantity}</p>
                <p>Price:₹{item.price}</p>
            </div>
        ))}
            </div>
            );
            }
    

export default OrderTracking;
