

import {useEffect,useState} from "react";
import API from "../services/api";
function Food(){
    const[foods,setFoods]=useState([]);
    const[categories,setCategories]=useState([]);
    const[foodName,setFoodName]=useState("");
    const[description,setDescription]=useState("");
    const[price,setPrice]=useState("");
    const[categoryId,setCategoryId]=useState("");
    const[availability,setAvailability]=useState(true);
    const[imgUrl,setImgUrl]=useState("");
    const loadFoods=()=>{API.get("/foods").then(response=>setFoods(response.data)).catch(error=>console.error(error));
    };
    useEffect(()=>{loadFoods();
        API.get("/categories").then(response=>setCategories(response.data)).catch(error=>console.error(error));
        },[]);
     const addFood=()=>{if(!foodName.trim()||!price||!categoryId){
        alert("Please fill all required fields");
        setImgUrl("");
        return;
     }
     const foodData={foodName,description,price:Number(price),availability,imgUrl,category:{categoryId:Number(categoryId)}

    };
    API.post("/foods",foodData).then(()=>{alert("Food Item added Sucessfully");
        setFoodName("");
        setDescription("");
        setPrice("");
        setCategoryId("");
        setAvailability(true);
        loadFoods();
    }).catch(error=>{console.error(error);
        alert("Unable to add food item");
    });
};
return(
<div>
    
     <h1>Food Maangement</h1>
    <input placeholder="Food Name" value={foodName} onChange={e=>setFoodName(e.target.value)}/>
    <br /><br />
    <input placeholder="Description" value={description} onChange={e =>setDescription(e.target.value)}
    />
    <br /><br />
    <input type="number" placeholder="Price" value={price} onChange={e=>setPrice(e.target.value)}/>
    <br /><br />
    <select value={categoryId} onChange={e=>setCategoryId(e.target.value)}>
        <option value="">Select Category</option>
        {categories.map(category=>(<option key={category.categoryId} value={category.categoryId}>
            {category.categoryName}
        </option>
    ))}
    </select>
    <br /><br />
    <label>
        <input type="checkbox" checked={availability} onChange={e =>setAvailability(e.target.checked)}/>
        Available
    </label>
    <br /><br />
    <button onClick={addFood}>Add Food</button>
    <h2>Food Items</h2>
    <div style={{
        display:"flex",
        flexWrap:"wrap",
        gap:"20px"}}>
    {foods.map(food=>(
        <div key={food.foodId} 
        style={{
            border:"1px solid gray",
            padding:"15px",margin:"15px",
            borderRadius:"10px",
            width:"280px"}}>
            <h3>{food.foodName}</h3>
            <img src={food.imgUrl}

            alt={food.foodName} 
            style={{width:"100%",heigth:"200px",
            objectFit:"cover",borderRadius:"10px"}}/>
            <p>{food.description}</p>
            <p>Price:{food.price}</p>
            <br />
   {/* <input palceholder="food Image Url" value={imgUrl} onChange={e=>setImgUrl(e.target.value)}/> */}
            <p>Status:{food.availability ?"Availabe":"Not Available"} </p>


    {/* //     <p key={food.foodId}>
    //     {food.foodName}-₹{food.price}-{food.availability ?"Available":"Not Available"}

    // </p> */}
    </div>
    ))}

</div>
</div>
); 
}
export default Food;