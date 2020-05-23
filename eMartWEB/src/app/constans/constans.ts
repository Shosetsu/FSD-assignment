export const Constants = Object.freeze({
    debugMode: true,
    routeAuthLevel: {
        'account': 'BSM',
        'detail/:uid': 'BSM',
        'detail': 'BSM',
        'delete': 'BS',
        'list': 'M',
        'block': 'M',
        'cart': 'BS',
        'order': 'BS',
        'order/detail/:oid': 'BS',
        'purchase': 'BS',
        'seller': 'S',
        'seller/:sid': 'SM',
        'edit/:gid': 'S',
        'new': 'S',
        'message': 'BSM'
    },
    goodStatus: [
        'normal', 'blocked', 'archived'
    ],
    serverAddress: 'http://localhost:10061',
    authPrefix: 'Bearer ',
    res_nothing: 0,
    res_error: 1,
    res_reload: 2,
    res_error_reload: 3,
    res_timeout: 4
});