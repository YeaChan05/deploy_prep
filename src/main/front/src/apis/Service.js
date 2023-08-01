import FetchTemplate from "./FetchTemplate";
const url = 'http://localhost:8080'
export const GetPosts = async () => {
    try {
        const response = await FetchTemplate({
            path: url + '/posts/all',
            method: 'GET',
        });
        const results = await response.json();
        return results;
    } catch (error) {
        console.log(error);
    }
}