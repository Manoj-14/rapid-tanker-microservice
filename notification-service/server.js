const client = require("cloud-config-client");
const express = require("express");
const {
  sendSupplierRegisterSuccessfullMsg,
} = require("./middleware/middleware");
const { CustomKafka } = require("./config/Kafka");
const { registerWithEureka } = require("./config/eureka-client");
const app = express();

client
  .load({
    application: "notification-service",
  })
  .then((config) => {
    process.env.port = config.get("server.port");
    broker = config.get("sasl.broker");
    saslMechanism = config.get("sasl.mechanism");
    saslUsername = config.get("sasl.username");
    saslPassword = config.get("sasl.password");
    process.env.node_email = config.get("nodemailer.email");
    process.env.app_password = config.get("nodemailer.password");

    registerWithEureka("notification-service", process.env.port);

    const kafka = new CustomKafka(
      broker,
      saslMechanism,
      saslUsername,
      saslPassword
    );

    kafka.subscribe(
      "notification",
      "notification_group",
      sendSupplierRegisterSuccessfullMsg
    );

    app.listen((port = process.env.port || 8080), () => {
      console.log("Server is running on port", port);
    });
  })
  .catch((err) => {
    console.log(err);
    process.exit(1);
  });
