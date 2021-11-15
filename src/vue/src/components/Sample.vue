<template>
    <div>
        <input v-model="url" /><br>
        <input type="button" value="GET SUBMIT" @click="getRequest" /><br>
        <input type="button" value="POST SUBMIT" @click="postRequest" /><br>
        {{ data }}
    </div>
</template>
<script>
export default {
    name: 'Sample',
    data () {
        return {
            sampleData: 'sampleData',
            url: '',
            data: {}
        }
    },
    created () {
        console.log('sample start ')
    },
    methods: {
        async getRequest () {
            this.data = await this.axios({
                url: this.url,
                method: 'get',
                params: {}
            })
        },
        async postRequest () {
            this.data = await this.axios({
                url: this.url,
                method: 'post',
                data: {}
            })
        }
    }
}
</script>