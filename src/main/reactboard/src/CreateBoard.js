import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function CreateBoard() {
  const navigate = useNavigate();

  // 폼 상태 관리
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [bigo, setBigo] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "https://13.53.74.46:8080/board",
        //"/board",
        {
          category,
          title,
          bigo,
        },
        { withCredentials: true } // 세션 유지
      );

      const data = response.data;
      if (data.result) {
        alert(data.msg);
        navigate("/"); // 등록 성공 시 목록 페이지로 이동
      } else {
        setErrorMsg(data.msg);
      }
    } catch (err) {
      console.error(err);
      setErrorMsg("서버와 통신 중 오류가 발생했습니다.");
    }
  };

  return (
    <div>
      <h2>글 작성 페이지</h2>
      {errorMsg && <p style={{ color: "red" }}>{errorMsg}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>카테고리:</label>
          <input
            type="text"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
          />
        </div>
        <div>
          <label>제목:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label>내용:</label>
          <textarea
            value={bigo}
            onChange={(e) => setBigo(e.target.value)}
            required
          />
        </div>
        <button type="submit">등록</button>
        <button type="button" onClick={() => navigate("/")}>
          목록으로
        </button>
      </form>
    </div>
  );
}

export default CreateBoard;
