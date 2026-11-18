

import { useEffect,useState} from "react";
import API from "../services/api";
import "./Dashboard.css"
function Dashboard(){
       const[dashboard,setDashboard]=useState({});
    const[data,setData]=useState({totalTables:0,
        availableTables:0,
        occupiedTables:0,
        totalFoodItems:0,
        totalOrders:0,
        pendingOrders:0,
        totalRevenue:0
    });
    const loadDashboard=async()=>{
        try{
            const response=await API.get("/dashboard");
            setDashboard(response.data);
        }catch(error){
            console.error("Dashboard Error:",error);
        }
    };
    useEffect(()=>{loadDashboard();
        const interval=setInterval(()=>{loadDashboard();},10000);
        return()=>clearInterval(interval);
    },[]);
    const [error,setError]=useState("");
    useEffect(()=>{API.get("/dashboard").then(response=>{setData(response.data);
        setError("");

    }).catch(error=>{console.error("Dashboard API Error:",error);
        setError("Unable to load dashboard data");

    });
},[]);
const cards=[{title:"Total Tables",value:dashboard.totalTables ?? 0,icon:"🪑",color:"blue"},
    {title:"Available Tables",value:dashboard.availableTables ?? 0,icon:"✅",color:"green"},
    {title:"Occupied Tables",value:dashboard.occupiedTables ?? 0,icon:"🍽️",color:"orange"},
    {title:"Total Food Items",value:dashboard.totalFoodItems ?? 0,icon:"🍕",color:"purple"},
    {title:"Total Orders",value:dashboard.totalOrders ?? 0,icon:"📋",color:"cyan"},
    {title:"Pending Orders",value:dashboard.pendingOrders ?? 0,icon:"⏳",color:"red"},
    {title:"Total Revenue",value:`₹${dashboard.totalRevenue}`,icon:"💰",color:"gold"}
];
return(
     <div className="dashboard-container">
          <h1>Restaurant Admin Dashboard</h1>
          <p className="dashboard-subtitle">
            welcome to your restaurant Management system</p>
               {error&&<p style={{color:"red"}}>{error}</p>}
                    <div className="dashboard-grid">
                      {cards.map((card,index)=>(
                        <div className="dashboard-card" key={index}>
                            <div className={`dashboard-icon ${card.color}`}>
                                {card.icon}
                                </div>
                             <div>   
                            <h3>{card.title}</h3>
                            <h2>{card.value}</h2>
                         </div>
                         </div>
                               ))}
                    </div>
        </div>
);
}
export default Dashboard;