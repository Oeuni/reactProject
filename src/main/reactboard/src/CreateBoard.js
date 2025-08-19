//import './BoardList.css';
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function CreateBoard() {
const navigate = useNavigate();
  return (
    <div>
      <h2>글 작성 페이지</h2>
      {/* 글 작성 폼 */}
      <button onClick={() => navigate("/")}>목록으로</button>
    </div>
  );
}

export default CreateBoard;