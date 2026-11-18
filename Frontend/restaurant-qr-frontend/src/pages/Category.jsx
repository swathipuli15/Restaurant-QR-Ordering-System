
import {useEffect,useState} from "react";
import API from "../services/api";
function Category(){
    
    const[categories,setCategories]=useState([]);
    const[categoryName,setCategoryName]=useState("");
    const loadCategories=()=>{API.get("/categories").then(response=>setCategories(response.data)).catch(error=>console.error(error));

    };
    useEffect(()=>{loadCategories();},[]);
    const addCategory=()=>{
        if(!categoryName.trim()){
        alert("Please enter category name");
        return;
    }
    API.post("/categories",{categoryName:categoryName}).then(() => { alert("Category added Suscessfully");
setCategoryName("");
loadCategories();
    }).catch(error=>console.error(error));

};
return (<div>
   
    <input type="text" placeholder="Enter category Name"value={categoryName} onChange={e=>setCategoryName(e.target.value)}/>
    <button onClick={addCategory}>Add Category</button>
    <h2>Category List</h2>
    {categories.map(category=>(<p key={category.categoryId}>{category.categoryName}</p>))}

</div>
);
}
export default Category;
