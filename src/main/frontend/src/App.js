import logo from './logo.svg';
import './App.css';
import { Route, Routes } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";

import Login from './pages/Login';
import Success from './pages/Success';

function App() {
  return (
    <BrowserRouter>
        <Routes>
          <Route exact path='/' element={<Login />}></Route>
          <Route exact path='/success' element={<Success/>}></Route>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
