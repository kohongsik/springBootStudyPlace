import axios from 'axios'
import Const from './Const.js'
export default {
    data () {
        return {
            state: 'NORMAL',
            CONST: Const
        }
    },
    methods: {
        log (msg = '') {
            console.log(msg)
        },
        makeHeaders () {
            return {
                headers: {
                    ...this.CONST.REQUEST_HEADER
                }
            }
        },
        axios (obj = {}) {
            const method = obj.method || 'get'
            return method === 'get'
                ? this.reqeustGetMethod(obj)
                : method === 'post' 
                    ? this.requestPostMethod(obj)
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
                    entry = entry + 'key=' + params[key]
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
                { ...data },
                { headers: this.CONST.REQUEST_HEADER })
                .then((response) => {
                    resp = response
                })
                .catch((e) => {
                    console.log('error : ', e)
                    alert(e)
                })
            return resp
        }
    }
}