var exec = require('cordova/exec');

exports.ignoreCerts = function (success, error) {
    exec(success, error, 'HTTPSTrustPlugin', 'ignoreCerts');
};
