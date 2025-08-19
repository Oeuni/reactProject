import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function BoardDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [board, setBoard] = useState(null);
  const [loading, setLoading] = useState(true);

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [hasPermission, setHasPermission] = useState(false);

  const [edit, setEdit] = useState(false);
  const [title, setTitle] = useState("");
  const [bigo, setBigo] = useState("");
  const [responseMsg, setResponseMsg] = useState("");

  // 게시글 상세 조회
  useEffect(() => {
    axios
      .get(`http://localhost:8080/board/${id}`, { withCredentials: true })
      .then((res) => {
        setBoard(res.data);
        setTitle(res.data.title);
        setBigo(res.data.bigo);
        setLoading(false);
      })
      .catch((err) => {
        console.error("게시글 상세 조회 실패:", err);
        setLoading(false);
      });
  }, [id]);

  // 로그인 + 권한 확인
  useEffect(() => {
    axios
      .get(`http://localhost:8080/board/${id}/check`, { withCredentials: true })
      .then((res) => {
        setIsLoggedIn(true);
        setHasPermission(res.data.result);
      })
      .catch(() => {
        setIsLoggedIn(false);
        setHasPermission(false);
      });
  }, [id]);

  // 수정 완료 처리
  const handleSave = () => {
    axios
      .put(
        `http://localhost:8080/board/${id}`,
        { seqno: id, title, bigo },
        { withCredentials: true }
      )
      .then((res) => {
        setResponseMsg(res.data.msg);
        setBoard({ ...board, title, bigo });
        setEdit(false);
      })
      .catch((err) => {
        console.error("게시글 수정 실패:", err);
        setResponseMsg("수정 실패");
      });
  };

  if (loading) return <div>게시글을 불러오는 중...</div>;
  if (!board) return <div>게시글이 존재하지 않습니다.</div>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>게시글 상세</h2>

      {responseMsg && <p style={{ color: "red" }}>{responseMsg}</p>}

      <p><b>번호:</b> {board.seqno}</p>
      <p><b>작성자:</b> {board.username}</p>
      <p><b>카테고리:</b> {board.category}</p>

      {edit ? (
        <>
          <p>
            <b>제목:</b>
            <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
          </p>
          <p>
            <b>내용:</b>
            <textarea
              value={bigo}
              onChange={(e) => setBigo(e.target.value)}
            />
          </p>
          <button onClick={handleSave}>저장</button>
          <button onClick={() => setEdit(false)}>취소</button>
        </>
      ) : (
        <>
          <p><b>제목:</b> {board.title}</p>
          <p><b>내용:</b> {board.bigo}</p>
          <button onClick={() => navigate("/")}>목록으로</button>

          {/* 로그인 & 권한 있으면 수정 버튼 표시 */}
          {isLoggedIn && hasPermission && (
            <button onClick={() => setEdit(true)}>수정</button>
          )}
        </>
      )}
    </div>
  );
}

export default BoardDetail;
