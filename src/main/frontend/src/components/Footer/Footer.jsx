import React from "react";

export default function Footer() {
  return (
    <footer className="p-4 bg-white sm:p-6">
      <div className="md:flex md:justify-between">
        <div className="mb-6 md:mb-0">
          <a href="/" className="flex items-center">
            <img
              src="/images/logo.webp"
              className="h-[80px]"
              alt="FlowBite Logo"
            />
          </a>
        </div>
      </div>
      <hr className="my-6 border-gray-200 sm:mx-auto dark:border-gray-700 lg:my-8" />
      <div className="sm:flex sm:items-center sm:justify-between">
        <span className="text-sm text-gray-500 sm:text-center">
          © 2023{" "}
          <a href="https://flowbite.com/" className="hover:underline">
            Mobile-ID JSC
          </a>
          . All Rights Reserved.
        </span>
      </div>
    </footer>
  );
}
