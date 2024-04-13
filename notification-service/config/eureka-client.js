const Eureka = require("eureka-js-client").Eureka;

const registerWithEureka = (appName, PORT) => {
  const client = new Eureka({
    // application instance information
    instance: {
      app: appName,
      hostName: process.env.NAMING_SERVER,
      ipAddr: process.env.NAMING_SERVER,
      port: {
        $: PORT,
        "@enabled": "true",
      },
      vipAddress: appName,
      dataCenterInfo: {
        "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
        name: "MyOwn",
      },
    },
    eureka: {
      // eureka server host / port
      host: process.env.NAMING_SERVER,
      port: 8761,
    },
  });

  client.logger.level("debug");

  client.start((error) => {
    console.log(error || "order service registered");
  });

  function exitHandler(options, exitCode) {
    if (options.cleanup) {
    }
    if (exitCode || exitCode === 0) console.log(exitCode);
    if (options.exit) {
      client.stop();
    }
  }

  client.on("deregistered", () => {
    console.log("after deregistered");
    process.exit();
  });

  client.on("started", () => {
    console.log("eureka host started");
  });

  process.on("SIGINT", exitHandler.bind(null, { exit: true }));
};

module.exports = { registerWithEureka };
