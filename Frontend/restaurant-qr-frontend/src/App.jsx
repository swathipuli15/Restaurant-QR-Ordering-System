
import { BrowserRouter,Routes,Route ,NavLink} from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Category from "./pages/Category";
import Food from "./pages/Food"
import Tables from "./pages/Tables";
import Menu from "./pages/Menu";
import Kitchen from "./pages/Kitchen";
import "./App.css";
import OrderHistory from "./pages/OrderHistory";
import OrderTracking from "./pages/OrderTracking";


function App(){
  return(
    // <div><h1>Restarunt QR Ordering System</h1></div>
    <BrowserRouter>
    <div className="app-layout">
      <aside className="sidebar">
      <h2>🍽️ Restaurant</h2>
      <p className="sidebar-subtitle">Management System</p>
      {/* <h1>Restarunt QR Ordering System</h1> */}
      <nav className="sidebar-nav">
        <NavLink to="/">🏠 Dashboard</NavLink>
        <NavLink to="/categories"> 📂Categories</NavLink>
        <NavLink to="/foods">🍕 Food Management</NavLink>
        <NavLink to="/tables">🪑Table Management</NavLink>
        <NavLink to="/kitchen">👨‍🍳 Kitchen Dashboard</NavLink>
        <NavLink to="/order-history">📜 Order History</NavLink>
      </nav>
      </aside>
   
  <main className="main-content">
    <header className="top-header">
      <div>
        <h2>Resturant Management</h2>
        <p>Welcome to your admin panel</p>
      </div>
      <div className="admin-profile">
        <span>📅 {new Date().toLocaleDateString()}</span>
        <span className="admin-avatar">👤</span>
        <strong>Admin</strong>
      </div>
    </header>
        <Routes>
      <Route path="/" element={<Dashboard />}/>
      <Route path="/categories" element={<Category />} />
      <Route path="/foods" element={< Food /> } /> 
      <Route path="/tables" element={<Tables />} />
      <Route path="/menu/:qrToken" element={<Menu />} />
      <Route path="/kitchen" element={<Kitchen />} />
      <Route path="/order-history" element={<OrderHistory/>} />
      <Route path="/track-order" element={<OrderTracking /> }/>
      </Routes>
      </main>
      </div>
      </BrowserRouter>
  );
}
export default App;