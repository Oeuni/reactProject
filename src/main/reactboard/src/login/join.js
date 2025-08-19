//import './BoardList.css';
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Join() {
 const navigate = useNavigate();
  return (
    <div>
      <h2>회원가입 페이지</h2>
      {/* 회원가입 폼 */}
      <button onClick={() => navigate("/")}>목록으로</button>
    </div>
  );
}

export default Join;