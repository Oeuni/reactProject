import { useState } from 'react';
import axios from 'axios'; // axios 임포트
import './App.css';

function App() {
  const [answer, setAnswer] = useState('');
  const [responseMsg, setResponseMsg] = useState('');
  const [isSuccess, setIsSuccess] = useState(null);

  const handleInputChange = (e) => {
    setAnswer(e.target.value);
  };

  const handleSubmit = async () => {
    try {
      // axios는 fetch와 달리 method 대신 axios.post()로 바로 호출 가능
      const response = await axios.post('/user', { answer });

      // response.data로 바로 접근 가능 (JSON 파싱 불필요)
      setResponseMsg(response.data.msg);
      setIsSuccess(response.data.result);
    } catch (error) {
      console.error('Error:', error);
      setResponseMsg('서버 요청 실패');
      setIsSuccess(false);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>이름 입력</h2>
      <input
        type="text"
        value={answer}
        onChange={handleInputChange}
        placeholder="이름을 입력하세요"
      />
      <button onClick={handleSubmit}>전송</button>

      {responseMsg && (
        <p style={{ color: isSuccess ? 'green' : 'red' }}>
          서버 응답: {responseMsg}
        </p>
      )}
    </div>
  );
}

export default App;
