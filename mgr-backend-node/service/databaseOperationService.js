const DatabaseOperation = require('../model/databaseOperation');

let DatabaseOperationService;
DatabaseOperationService.readAll = async (req, res) => {
    try {
        const users = await DatabaseOperation.findAll();
        return res.send({ users });
    } catch (error) {
        return res.send(error);
    }
};

module.exports = DatabaseOperationService;