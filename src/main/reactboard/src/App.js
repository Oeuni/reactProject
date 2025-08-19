import './App.css';
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BoardList from "./BoardList";
import BoardDetail from "./BoardDetail";
import Login from "./login/login";
import Join from "./login/join";
import CreateBoard from "./CreateBoard";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<BoardList />} />
        <Route path="/board/:id" element={<BoardDetail />} />
        <Route path="/login" element={<Login />} />
        <Route path="/join" element={<Join />} />
        <Route path="/board" element={<CreateBoard />} />
      </Routes>
    </Router>
  );
}

export default App;
