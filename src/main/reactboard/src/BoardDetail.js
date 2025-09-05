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
  const [responseColor, setResponseColor] = useState("red");    // 수정 결과 메시지 색상
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [bigo, setBigo] = useState("");
  const [responseMsg, setResponseMsg] = useState("");

  // 게시글 상세 조회
  useEffect(() => {
    axios
      .get(`http://13.49.228.166:8080/board/${id}`, { withCredentials: true })
      //.get(`http://localhost:8080/board/${id}`, { withCredentials: true })
      .then((res) => {
        console.log("data :: ", res.data);

        setBoard(res.data);
        setCategory(res.data.category);
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
      .get(`http://13.49.228.166:8080/board/${id}/check`, { withCredentials: true })
      //.get(`http://localhost:8080/board/${id}/check`, { withCredentials: true })
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
        `http://13.49.228.166:8080/board/${id}`,
        //`http://localhost:8080/board/${id}`,
        { seqno: id, category, title, bigo },
        { withCredentials: true }
      )
      .then((res) => {
        setResponseMsg(res.data.msg);
        setResponseColor("green");  // 성공 메시지는 초록색
        setBoard({ ...board, category, title, bigo });
        setEdit(false);
      })
      .catch((err) => {
        console.error("게시글 수정 실패:", err);
        setResponseMsg("수정 실패");
        setResponseColor("red");  // 실패 메시지는 빨간색
      });
  };

  // 삭제 처리
  const handleDelete = () => {
    if (window.confirm("삭제하시겠습니까?")) {
      axios
        .delete(`http://13.49.228.166:8080/board/${id}`, { withCredentials: true })
        //.delete(`http://localhost:8080/board/${id}`, { withCredentials: true })
        .then((res) => {
          alert(res.data.msg);
          if (res.data.result) {
            navigate("/"); // 삭제 성공 시 목록으로 이동
          }
        })
        .catch((err) => {
          console.error("게시글 삭제 실패:", err);
          alert("삭제 중 오류가 발생했습니다.");
        });
    }
  };

  if (loading) return <div>게시글을 불러오는 중...</div>;
  if (!board) return <div>게시글이 존재하지 않습니다.</div>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>게시글 상세</h2>

      {responseMsg && <p style={{ color: responseColor }}>{responseMsg}</p>}

      <p><b>번호:</b> {board.seqno}</p>
      <p><b>작성자:</b> {board.username}</p>


      {edit ? (
        <>
      {/*수정모드*/}
         <p>
           <b>카테고리:</b>
           <input
             type="text"
             value={category}
             onChange={(e) => setCategory(e.target.value)}
          />
         </p>
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
      {/*읽기모드*/}
          <p><b>카테고리:</b> {board.category}</p>
          <p><b>제목:</b> {board.title}</p>
          <p><b>내용:</b> {board.bigo}</p>
          <button onClick={() => navigate("/")}>목록으로</button>

          {/* 로그인 & 권한 있으면 수정, 삭제 버튼 표시 */}
          {isLoggedIn && hasPermission && (
            <>
              <button onClick={() => setEdit(true)}>수정</button>
              <button onClick={handleDelete}>삭제</button>
            </>
          )}
        </>
      )}
    </div>
  );
}

export default BoardDetail;
