import fetch from 'cross-fetch';

function client(endpoint, config = {}) {
    var defaultConifg = {
        method: 'GET',
        headers: {
            'content-type': 'application/json'
        }
    };

    config = Object.assign(defaultConifg, config);

    return fetch(`${process.env.REACT_APP_API_URL}/${endpoint}`, config)
        .then(response => {
            if (!response.ok) {
                throw Error(response.statusText);
            }

            return response.json();
        });
}

export function get(endpoint) {
    return client(endpoint);
}

export function post(endpoint, body) {
    let config = {
        method: 'POST',
        body: JSON.stringify(body)
    };
    
    return client(endpoint, config);
}