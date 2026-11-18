

import {useEffect,useState} from "react";
import {useParams} from "react-router-dom";
import API from "../services/api";

function Payment(){
    const{billId}=useParams();
    const[bill,setBill]=useState(null);
    const[paymentMethod,setPaymentMethod]=useState("UPI");
}