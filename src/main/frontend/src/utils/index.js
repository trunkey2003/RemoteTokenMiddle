export const isValidPhoneNumber = (phoneNumber) => {
    var re = /(((\+)84)|0)(3|5|7|8|9)+([0-9]{8})\b/;
    if (phoneNumber.length < 10) return null;
    return re.test(phoneNumber);
  };

export const isValidEmail = (email) => {
  var re = /\S+@\S+\.\S+/;
  if (email.length < 6) return null;
  return re.test(email);
};

export const handleInputNumber = (e) => {
  e.target.blur();
}
  