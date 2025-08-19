import './BoardList.css';
import React, { useEffect, useState } from "react";
import axios from "axios";

function BoardList() {
  const [boards, setBoards] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost:8080/board", { withCredentials: true })
      .then((response) => {
        setBoards(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("게시글 로딩 실패:", error);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>게시글을 불러오는 중...</div>;

  return (
    <div style={{ padding: "20px" }}>
      {/* 상단 버튼 영역 */}
      <div className="top-buttons">
        <button onClick={() => window.location.href="/login"}>로그인</button>
        <button onClick={() => window.location.href="/join"}>회원가입</button>
      </div>

      {/* 글 작성 버튼 */}
      <div className="write-button">
        <button onClick={() => window.location.href="/board"}>글 작성</button>
      </div>

      <h1>게시글 목록</h1>
      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>번호</th>
            <th>작성자</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>내용</th>
          </tr>
        </thead>
        <tbody>
          {boards.length === 0 ? (
            <tr>
              <td colSpan="5">등록된 게시글이 없습니다.</td>
            </tr>
          ) : (
            boards.map((board) => (
              <tr
                key={board.seqno}
                className="board-row"
                style={{ cursor: "pointer" }}
                onClick={() => (window.location.href = `/board/${board.seqno}`)}
              >
                <td>{board.seqno}</td>
                <td>{board.username}</td>
                <td>{board.category}</td>
                <td>{board.title}</td>
                <td>{board.bigo}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default BoardList;
