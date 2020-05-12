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
    fetchAddress: 'http://localhost:10048',
    fetchHeader: {
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Accept': 'application/json',
        'Content-Type': 'application/json',        
    }
});