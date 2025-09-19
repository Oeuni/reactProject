import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Join() {
  const navigate = useNavigate();
  const [userid, setUserid] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [checkMsg, setCheckMsg] = useState("");             // 아이디 중복확인 결과 메시지
  const [checkMsgColor, setCheckMsgColor] = useState("");   // 메시지 색상
  const [errorMsg, setErrorMsg] = useState("");             // 회원가입 실패 메시지

  // 아이디 중복확인
  const handleCheckUserid = async () => {
    if (!userid) {
      setCheckMsg("아이디를 입력해주세요.");
      return;
    }
    try {
      const res = await axios.get("https://13.53.74.46:8080/user", {
      //const res = await axios.get(`http://localhost:8080/user`, {
        params: { userid },
      });
    setCheckMsg(res.data.msg);
    if (res.data.result) {
      // true = 사용 가능한 아이디
      setCheckMsgColor("green");
    } else {
      // false = 이미 사용중
      setCheckMsgColor("red");
    }
    } catch (error) {
      console.error("아이디 중복확인 실패", error);
      setCheckMsg("서버 오류가 발생했습니다.");
    }
  };

  // 회원가입 처리
  const handleJoin = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("https://13.53.74.46:8080/join", {
    //const res = await axios.post("http://backend:8080/join", {
        userid,
        password,
        username,
      });

      if (res.data.result) {
        alert("회원가입 성공!");
        navigate("/login"); // 회원가입 후 로그인 페이지로 이동
      } else {
        setErrorMsg(res.data.msg);
      }
    } catch (error) {
      console.error("회원가입 요청 실패", error);
      setErrorMsg("서버 오류가 발생했습니다.");
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "50px auto" }}>
      <h2>회원가입 페이지</h2>
      <form onSubmit={handleJoin}>
        <div>
          <label>아이디</label>
          <input
            type="text"
            value={userid}
            onChange={(e) => setUserid(e.target.value)}
            required
          />
          <button type="button" onClick={handleCheckUserid}>
            중복확인
          </button>
          {checkMsg && <p style={{ color: checkMsgColor }}>{checkMsg}</p>}
        </div>

        <div>
          <label>비밀번호</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <div>
          <label>이름</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>

        {errorMsg && <p style={{ color: "red" }}>{errorMsg}</p>}

        <button type="submit">회원가입</button>
        <button onClick={() => navigate("/")}>목록으로</button>
      </form>
    </div>
  );
}

export default Join;
