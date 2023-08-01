const FetchTemplate = ({path, method, needToken = false, token, body}) => {
    const headers = {} 
    
    if (method !== "GET") {
        headers["Content-Type"] = "application/json";
    }

    if (needToken) {
        headers["Access-Token"] = token;
    }

    return fetch(path, {
        method,
        headers,
        body,
    });
}

export default FetchTemplate;