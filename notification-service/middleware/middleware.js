const { NodeMail } = require("../config/Mail");

const sendSupplierRegisterSuccessfullMsg = (data) => {
  const { email, name } = data.supplier;
  const mail = new NodeMail();
  mail.sendMail(email, name);
};

module.exports = { sendSupplierRegisterSuccessfullMsg };
