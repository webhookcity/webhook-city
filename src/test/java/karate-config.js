function fn() {
    var port = karate.properties['local.server.port'];

    var config = { // base config JSON
        localApi:'http://localhost:' + port,
        host: 'localhost:' + port
    };

    // don't waste time waiting for a connection or if servers don't respond within 5 seconds
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    return config;
}