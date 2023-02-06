import {useState,useEffect} from "react";
import Axios from "../../utils/Axios";
import {useSelector } from "react-redux";
import {message} from "antd";

export default function Nav() {
  const userInfo = useSelector((state) => state.userInfo); 
  const [classSubNav, setClassSubNav] = useState("hidden md:block");

  const handleToogleSubNav = () => {
    setClassSubNav(classSubNav === "hidden md:block" ? "block md:block" : "hidden md:block");
  };

  const handleSignOut = () => {
    Axios.delete("/auth/sign-out").then(() => {
      message.success("Đăng xuất thành công");
      window.location.href = "/";
    })
  };

  return (
    <nav className="h-[80px] flex items-center bg-white border-gray-200 relative">
      <div className="container flex flex-wrap items-center justify-between mx-auto">
        <a href="/" className="flex items-center">
          <img
            src="/images/logo.webp"
            className="ml-5 md:ml-0 h-[50px]"
            alt="Flowbite Logo"
          />
          <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
            Flowbite
          </span>
        </a>
        <button
          data-collapse-toggle="navbar-default"
          type="button"
          className="inline-flex items-center p-2 ml-3 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
          aria-controls="navbar-default"
          aria-expanded="false"
          onClick={handleToogleSubNav}
        >
          <span className="sr-only">Open main menu</span>
          <svg
            className="w-6 h-6"
            aria-hidden="true"
            fill="currentColor"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fillRule="evenodd"
              d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
              clipRule="evenodd"
            ></path>
          </svg>
        </button>
        <div className={`${classSubNav} top-[100%] absolute lg:top-0 lg:relative w-full md:w-auto`} id="navbar-default">
          <ul className="flex flex-col p-4 border border-gray-100 bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white">
            <li>
              Hello user {userInfo?.username}
            </li>
            <li>
              <button
                onClick={handleSignOut}
                className="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0"
              >
                Sign Out
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
