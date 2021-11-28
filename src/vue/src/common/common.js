import axios from 'axios'

export default ({
    name: 'common',
    methods: {
        axios (obj = {}) {
            const method = obj.method || 'get'
            return method === 'get'
                ? this.reqeustGetMethod(obj)
                : method === 'post' 
                    ? this.requestPostMethod(obj)
                    : method === 'put'
                        ? this.requestPutMethod(obj)
                        : method === 'delete'
                            ? this.requestdDeleteMethod(obj)
                            : {}
        },
        async reqeustGetMethod (obj = {}) {
            const params = obj.params || {}
            const paramsKey = Object.keys(params) || []
            let url = obj.url || ''
            if (paramsKey.length > 0) {
                const queryString = paramsKey.reduce((entry, key) => {
                    if (entry) {
                        entry = entry + '&'
                    } 
                    entry = entry + key + '=' + params[key]
                    return entry
                }, '')
                url += '/?' + queryString
            }
            let resp = {}
            await axios.get(
                url)
                .then((response) => {
                    resp = response
                })
                .catch((e) => {
                    console.log('error : ', e)
                    alert(e)
                })
            return resp
        },
        async requestPostMethod (obj = {}) {
            const data = obj.data || {}
            let resp = {}
            await axios.post(obj.url,
                { ...data })
                .then((response) => {
                    resp = response
                })
                .catch((e) => {
                    console.log('error : ', e)
                    alert(e)
                })
            return resp
        },
        async requestPutMethod (obj = {}) {
            const data = obj.data || {}
            let resp = {}
            await axios.put(obj.url,
                { ...data })
                .then((response) => {
                    resp = response
                })
                .catch((e) => {
                    console.log('error : ', e)
                    alert(e)
                })
            return resp
        },
        async requestdDeleteMethod (obj = {}) {
            const data = obj.data || {}
            let resp = {}
            await axios.delete(obj.url,
                { ...data })
                .then((response) => {
                    resp = response
                })
                .catch((e) => {
                    console.log('error : ', e)
                    alert(e)
                })
            return resp
        },
        goto (url = '', params = {}) {
            this.$router.push({path: url, query: params})
        }
    }
})