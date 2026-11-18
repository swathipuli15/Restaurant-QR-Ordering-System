
// import {useState,useEffect} from "react";
import API from "../services/api";
import {useState,useEffect,useRef} from "react";
function OrderHistory(){
    const[orders,setOrders]=useState([]);
     const[message,setMessage]=useState("Loading orders...");
     const loadOrders=async()=>{
        try{
            const response=await API.get("/orders");
            setOrders(response.data);
            setMessage("Order history loaded Sucessfully");
        }catch(error){
            console.error("Order Histroy Error",error);
            setMessage("Unable to load History");
        }
     };
     useEffect(()=>{loadOrders();},[]);
    // const[filter,setFilter]=useState("ALL");
    // const[search,setSearch]=useState("");
    // const[message,setMessage]=useState("Loading orders...");
    //  const orderListRef=useRef(null);
    //  const[selectedOrder,setSelectedOrder]=useState(null);
    // const filteredOrders=orders.filter(order=>{
        // const searchText=search.trim().toLowerCase();
        // const orderId=String(order.orderId ?? "");
        // const tableNumber=String(order.tableSession?.restaurantTable?.tableNumber??"");
        // const foodNames=(order.orderItem??[]).map(item=>item.foodItem?.foodName??"").join(" ").toLowerCase();
        // const matchesSearch=orderId.includes(searchText)||tableNumber.includes(searchText)||foodNames.includes(searchText);
        // const matchesStatus=filter==="ALL"||order.orderStatus?.toUpperCase()===filter;
        // <button onClick={()=>setSelectedOrder(order)}>View Deatils</button>
        // const matchesStatus=filter==="ALL" ||order.orderStatus?.toUpperCase()===filter;
        // const matchesSearch=order.orderId?.toString().includes(search)||order.tableSession?.restaurantTable?.tableNumber?.toString().includes(search)||
        // order.orderItem?.some(item=>item.foodItem?.foodName?.toLowerCase().includes(search.toLowerCase()));
        // return matchesStatus&&matchesSearch;
    // });
    // const printOrder=()=>{window.print();};

    // useEffect(()=>{if(search.trim()!=="" && filteredOrders.length>0){
        // orderListRef.current?.scrollIntoView({
            // behavior:"smooth",
            // block:"start"
        // });
    //    }
    //   },[search]);
    // const loadOrders=async()=>{
    //     try{
    //         const response=await API.get("/kitchen/orders");
    //         if(Array.isArray(response.data)){
    //             setOrders(response.data);
    //             setMessage(response.data.length==0?"NO orders found":"Order history loaded sucssfully");

    //         }
    //     }catch(error){
    //         console.error("Order History Error:",error);
    //         setMessage("Unable to load orderHistory");

    //     }
    // };
//     useEffect(()=>{const handleEscape=(event)=>{if(event.key==="Escape"){
//         setSelectedOrder(null);
//     }};
//     window.addEventListener("keydown",handleEscape);
//     return()=>{window.removeEventListener("keydown",handleEscape);

//     };
// },[]);

    // useEffect(()=>{loadOrders();},[]);
    // const exportOrders=()=>{
    //     const headers=["Order Id","Table Number","Food Items","Total Amount","Status","Order Time"];
    //     const rows=filteredOrders.map(order=>[order.orderId,order.tableSession?.restaurantTable?.tableNumber??"",
    //         (order.orderItem??[]).map(item=>`${item.foodItem?.foodName??""}x${item.quantity}`).join("; "),
    //         order.totalAmount,
    //         order.orderStatus,
    //         order.orderTime
    //     ]);
    //     const csvContent=[headers,...rows].map(row=>row.map(value=>`"${String(value??"").replace(/"/g,'""')}"`).join(",")).join("\n");
    // const blob =new Blob(["\uFEFF" +csvContent],{type:"text/csv;charset=utf-8;"

    // });
    // const url=URL.createObjectURL(blob);
    // const link=document.createElement("a");
    // link.href=url;
    // link.download="restaurant-order-history.csv";
    // link.click();
    // URL.revokeObjectURL(url);
    
    // };
    // const filteredOrders=orders.filter(order=>filter==="ALL" ||order.orderStatus?.toUpperCase()===filter);
// return(
        // <>
          
        {/* <style> */}
   {/* {`
@media print {
    body * {
        visibility: hidden;
    }
        .print-section,
        .print-section *{
        visibility:visible
        }
        .print-section{
        display:block !important;
        position:absolute;
        top:0;
        left:0;
        width:100%;
        padding:20px;
        background:white;
        color:black;
        }

    .order-modal,
    .order-modal * {
        visibility: visible !important;
    }

    .order-modal {
        position: absolute !important;
        left: 0 !important;
        top: 0 !important;
        width: 100% !important;
        max-width: 100% !important;
        max-height: none !important;
        overflow: visible !important;
        background: white !important;
        color: black !important;
    }

    .no-print {
        display: none !important;
    }
}
`}
        </style> */}
 return(
    <div
     style={{padding:"30px"}}>
        <h1>Order History</h1>
        <p>{message}</p>
        {orders.map(order=>(<div key={order.orderId}
        style={{border:"1px solid gray",padding:"15px",margin:"15px 0",borderRadius:"8px"}}>
            <h3>Order #{order.orderId}</h3>
            <p>Table number{" "} {order.tableSession?.restaurantTable?.tableNumber}</p>
            <h4>Food Items</h4>
            {order.orderItem?.map(item=>(<p key={item.itemId}>{item.foodItem?.foodName}x{item.quantity}{"-₹"}{item.price*item.quantity} </p>
            ))}
            <h3>Total: ₹{order.totalAmount}</h3>
            <p>Status:{order.orderStatus}</p>
            <p>Order Time:{order.orderTime}</p>
            </div>
        ))}
        </div>
    );
}
export default OrderHistory;
//         <button onClick={exportOrders} style={{background:"#16a34a",
//             color:"white",
//             padding:"12px 20px",
//             border:"none",
//             borderRadius:"6px",
//             cursor:"pointer",marginBottom:"15px"
//         }}>📥 Export Orders to CSV</button>
//         <input type="text" placeholder="Search by OrderID,Table Number or Food Item..."
//                    value={search} 
//                      onChange={e=>setSearch(e.target.value)}
//        style={{
//                width:"100%",
//                padding:"12px",
//                marginBottom:"20px",
//                border:"1px solid #cbd5e1",
//                borderRadius:"6px"}}/>
        
//         <div style={{display:"flex",gap:"10px",marginBottom:"20px" ,flexWrap:"wrap"}}>
//             {["ALL","PENDING","PREPARING","COMPLETED"].map(status=>(
//                 <button key={status} onClick={()=> setFilter(status)}
//             style={{padding:"10px 18px",
//             border:"none",
//         borderRadius:"6px",
//         cursor:"pointer",
//     background:filter===status?"#2563eb":"#e2e8f0",
// color:filter===status?"white":"#1e293b"}}>
//     {status==="ALL" ?"All Orders" :status}

// </button>))}
  
    
//         </div >
//         <div ref={orderListRef}>
//         {filteredOrders.map(order=>(
//             <div key={order.orderId}  style={{
//             background:"white",
//             padding:"20px",
//             marginBottom:"15px",
//             borderRadius:"10px",
//             boxShadow:"0 2px 8px #ddd"}}>
//             <h2>Order #{order.orderId}</h2>
//             <p>Table Number:{order.tableSession?.restaurantTable?.tableNumber}</p>
//             <h3>Food Item</h3>
//             {order.orderItem?.map(item=>(
//                 <p key={item.itemId}>
//                 {item.foodItem?.foodName}
//                 {"x"}
//                 {item.quantity}
//                 {"- ₹"}
//                 {item.price}
//             </p>
//         ))}
        
//             <h3>Total:₹{order.totalAmount} </h3>
//             <p>Status:{""}
//                 <span style={{
//                     padding:"6px 12px",
//                     borderRadius:"20px",
//                     fontWeight:"bold",
//                     fontSize:"13px",
//                     background:order.orderStatus==="COMPLETED"?"#dcfce7":
//                     order.orderStatus==="PREPARING"?"#fef3c7":"#fee2e2",
//                     color:order.orderStatus==="COMPLETED"?"#166534":
//                     order.orderStatus==="PREPARING"?"#92400e":"#991b1b"
//                 }}>
//                     {order.orderStatus}
//                     </span></p>
//             <p>Order Time:{" "}
//             {new Date(order.orderTime).toLocaleString("en-IN",{
//                 day:"2-digit",
//                 month:"short",
//                 year:"numeric",
//                 hour:"2-digit",
//                 minute:"2-digit",
//                 hour12:true
//                 // <button onClick={()=>setSelectedOrder(order)}>view details</button>
        
            
// })}
//             </p>
//                <button onClick={()=>setSelectedOrder(order)}>view details</button>
            
//             </div>
//         ))}
//     </div>
//     {selectedOrder && (
//         <div className="no-print-overlay"
//         onClick={()=>setSelectedOrder(null)}
//         style={{
//             position:"fixed",
//             top:0,
//             left:"20%",
//             width:"100%",
//             height:"100%",
//             background:"rgba(0,0,0,0.6)",
//             dispaly:"flex",
//             justifyContent:"center",
//             alignItems:"center",
//             zIndex:1000,
//             overflowY:"auto"

//         }}>
//             <div className="order-modal" onClick={(e)=>e.stopPropagation()}
//             style={{background:"white",
//                 padding:"20px",
//                 width:"90%",
//                 maxWidth:"500px",
//                  maxHeight:"80vh",
//                  overflowY:"auto",borderRadius:"10px"
//             }}>
//                 </div>
//         <div className="print-section" 
//          style={{
//             background:"white",
//             padding:"20px",
//             width:"90%",
//             maxWidth:"500px",
//             maxHeight:"80vh",
//             overflowY:"auto",
//             borderRadius:"10px",
//             // boxShadow:"0 2px 8px #ddd"
//         }}>
//         <h2>OrderDetails</h2>
//     <p>Order Id:{selectedOrder.orderId}</p>
//     <p>Table Number:{" "}{selectedOrder.tableSession?.restaurantTable?.tableNumber}</p>
//     <h3>Food Items</h3>
//     {selectedOrder.orderItem?.map(item=>(
//         <div  key={item.itemId}
//          style={{
//         display:"flex",
//     alignItems:"center",
//     gap:"15px",
//     marginBottom:"15px",
//     padding:"10px",
//     border:"1px solid #ddd",
//     borderRadius:"8px"
//     }}>
//         <img src={item.foodItem?.imgUrl||"/food-placeholder.png"}
//         alt={item.foodItem?.foodName || "FoodItem"}
//         onError={(e)=>{e.currentTarget.oneerror=null;
//             e.currentTarget.src="/food-palceholder.png";
//         }}
//         style={{width:"80px",
//             height:"80px",
//             objectFit:"cover",
//             borderRadius:"8px"
//             }}/>
//             <div>
//                 <h3>{item.foodItem?.foodName}</h3>
//                 <p>Quantity:{item.quantity}</p>
//                 <p>Price:₹{item.price}</p>
//                 </div>
//                </div>
 

        
//     ))}
//         <h3>Total:₹{selectedOrder.totalAmount}</h3>

//         <p>Status:{selectedOrder.orderStatus} </p>
//         <p>OrderTime:{" "}{new Date(selectedOrder.orderTime).toLocaleString("en-IN")}</p>
//         <button className="no-print"
//         onClick={()=>setSelectedOrder(null)}
//                 style={{background:"#dc2626",
//                     color:"white",
//                     padding:"10px 20px",
//                     border:"none",
//                     borderRadius:"6px",
//                     cursor:"pointer"}}>
//                         Close
//         </button>
//         <button className="no-print" onClick={printOrder}>Print Order</button>
//         </div>
//         </div>
//         )}
//         {/* )))} */}
//     </div>
  
// //   </>
//     );

// }
// export default OrderHistory;
