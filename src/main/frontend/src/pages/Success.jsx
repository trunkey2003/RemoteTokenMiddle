import { useState, useEffect } from "react";
import { Axios } from "../util/Axios";

export default function Success() {
  const [user, setUser] = useState({});
  useEffect(() => {
    Axios.get("/api/v1/auth/check-auth")
      .then((res) => {
        setUser({ username: res.data.username });
      })
      .catch(() => {
        window.location.href = "/";
      });
  }, []);

  const handleSignOut = () => {
    Axios.delete("/api/v1/auth/logout")
      .then(() => {
        window.location.href = "/";
      })
      .catch(() => {
        alert("fail");
      });
  }


  return (
    <div className="flex items-center justify-center h-screen">
      <div>
        <div className="text-[40px]">Chào mừng user {user.username}</div>
        <div className="text-center">
          <button className="bg-black px-10 py-4 text-white" onClick={handleSignOut}>Đăng Xuất</button>
        </div>
      </div>
    </div>
  );
}
