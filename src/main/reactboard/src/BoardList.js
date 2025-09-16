import './BoardList.css';
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function BoardList() {
  const navigate = useNavigate();
  const [boards, setBoards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [loggedIn, setLoggedIn] = useState(false); // 로그인 상태
  const [userId, setUserId] = useState("");        // 로그인한 사용자 아이디

  // 게시글 로딩
  useEffect(() => {
    axios
      .get("/board", { withCredentials: true })
      //.get("http://localhost:8080/board", { withCredentials: true })
      .then((response) => {
        setBoards(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("게시글 로딩 실패:", error);
        setLoading(false);
      });
  }, []);

  // 로그인 상태 확인
  useEffect(() => {
    axios
      .get("/auth/check", { withCredentials: true })
      //.get("http://localhost:8080/auth/check", { withCredentials: true })
      .then((res) => {
        if (res.data.loggedIn) {
          setLoggedIn(true);
          setUserId(res.data.userId);
        } else {
          setLoggedIn(false);
        }
      })
      .catch((err) => console.error("로그인 체크 실패:", err));
  }, []);

  // 로그아웃 처리
  const handleLogout = () => {
    axios.post("/logout", {}, { withCredentials: true })
    //axios.post("http://localhost:8080/logout", {}, { withCredentials: true })
      .then(() => {
        setLoggedIn(false);
        setUserId("");
        window.location.reload();
      })
      .catch((err) => console.error("로그아웃 실패:", err));
  };

    // 글 작성 버튼 눌렀을 때
    const writeCheck = () => {
      if (loggedIn) {
        navigate("/board");
      }
      else {
        alert("로그인이 필요한 서비스입니다.");
        //window.location.href = "/login";
        navigate("/login");
      }
    };

  if (loading) return <div>게시글을 불러오는 중...</div>;

  return (
    <div style={{ padding: "20px" }}>
      {/* 상단 버튼 영역 */}
      <div className="top-buttons">
        {loggedIn ? (
          <>
            <span>{userId}님 환영합니다 😊</span>
            <button onClick={handleLogout}>로그아웃</button>
          </>
        ) : (
          <>
            {/*<button onClick={() => (window.location.href = "/login")}>로그인</button>*/}
            <button onClick={() => navigate("/login")}>로그인</button>
            {/*<button onClick={() => (window.location.href = "/join")}>회원가입</button>*/}
            <button onClick={() => navigate("/join")}>회원가입</button>
          </>
        )}
        <button onClick={writeCheck}>글 작성</button>
      </div>

      <h1>🎀게시글 목록🎀</h1>
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
                onClick={() => navigate(`/board/${board.seqno}`)}
              >
                <td>{board.seqno}</td>
                <td>{board.username}</td>
                <td>{board.category === "F" ? "자유게시판" : board.category}</td>
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
