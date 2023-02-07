import React from "react";
import Footer from "../components/Footer/Footer";
import Nav from "../components/Nav/Nav";

export default function MainLayout({ children }) {
  return (
    <div>
      <Nav />
      <div
        className="min-h-screen bg-cover bg-center"
        style={{
          backgroundImage: `url(${require('../assets/images/login-bg.png')})`,
        }}
      >
        {children}
      </div>
      <Footer />
    </div>
  );
}
