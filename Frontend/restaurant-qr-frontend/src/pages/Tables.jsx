import {useEffect,useState} from "react";
import API from "../services/api";

function Tables(){
    const[tables,setTables]=useState([]);
    const[tableNumber,setTableNumber]=useState("");
    const[qrCode,setQrCode]=useState("");

    const loadTables=async () =>{
    try{
        const response =await API.get("/tables");
        setTables(response.data);
    }catch(error){
        console.error("Error loading tables:" ,error);
    }
};
    useEffect(()=>{loadTables();},[]);
    const addTable=async()=>{if(!tableNumber){alert("please enter table Number");
        return;
    }
    try{
        await API.post("/tables",{tableNumber:Number(tableNumber)});
        alert("Table added Sucessfully");
        setTableNumber("");
        loadTables();
    }catch(error){
        console.error(error);
        alert("Unable to add table");

    }
    };
    const generateQR=async(tableId)=>{try{const response=await API.get(`/tables/${tableId}/qr`);
setQrCode(response.data);
}catch(error){
    console.error(error);
    alert("Unable to generate QR code");
}};

return(

    <div style={{padding:"30px"}}>
    <h1>Table Managemet</h1>
     <input type="number" placeholder="Enter table Nmber" value={tableNumber} onChange={e=>setTableNumber(e.target.value)}/>
    <button onClick={addTable}>Add Table</button>
    <h2>Restaurant Tables</h2>
   <h3>Total Tables:{tables.length}</h3>
    {tables.map(table =>(<div key={table.tableId}
    style={{border:"1px solid gray",padding:"15px",margin:"10px",borderRadius:"8px"}}>
    <p> Table Number: {table.tableNumber}</p>
    <p>Status:{table.status}</p>
 <button onClick={()=>generateQR(table.tableId)}>
    Generate QR Code
</button>
</div>
))}
 {qrCode &&(<div>
    <h2>Table QR Code</h2>
     <img src={qrCode} alt="Table QR Code" width="200" />
     </div>
 )} 

     </div>
);  
     }

export default Tables;