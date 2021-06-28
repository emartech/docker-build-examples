"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const fastify_1 = __importDefault(require("fastify"));
const server = fastify_1.default();
// Declare a route
server.get('/', function (request, reply) {
    reply.send({ hello: 'world' });
});
// Run the server!
server.listen(3000, '0.0.0.0', function (err, address) {
    if (err) {
        server.log.error(err);
        process.exit(1);
    }
    server.log.info(`server listening on ${address}`);
});
//# sourceMappingURL=server.js.map