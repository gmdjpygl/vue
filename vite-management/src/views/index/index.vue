<!--  -->
<template>
  <div>
    <button v-bind:disabled="c">  C  BUTTON</button>
    <div v-bind="b">  B  DIV</div>
    <div v-bind:name="a"> A DIV</div>
    {{ number + 1 }}<br>
    {{ number + 1 }}<br>
    <button @click="n++">N增加1 </button>
    <br>
    <button @click="pn">读取N</button>
{{ ok ? 'YES' : 'NO' }}

{{ message.split('').reverse().join('') }}

<div :id="`list-${id}`+id">ID1</div>


</div>


  <myPie :data="data" :a='a' />
</template>

<script lang='ts' setup>
import { reactive, toRefs, ref } from 'vue'
import myPie from './components/myPie.vue'
let n=ref(1);
let number:number=1;
let ok=true;
let message='人人有我';
let id='id1';
let c=true;
const b:{} = {
  id:'b1'
}
const pn=()=>{console.log(n.value);}



const state = reactive<{
  data: { seriesName: string; xAxisData: string[]; seriesData: number[] }[],
  // data:{}[]
  a: string
}>({
  data: [],
  a: ''
})
const { data, a } = toRefs(state)

let timeout: NodeJS.Timeout

timeout = setTimeout(() => {
  console.log('------timeout---------');
  data.value = [{ seriesName: 'ddd', xAxisData: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'], seriesData: [5, 20, 36, 10, 10, 20] }];
  //data.value.push({ seriesName: 'ddd', xAxisData: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'], seriesData: [5, 20, 36, 10, 10, 20] });
  console.log(data.value);
  a.value = '11';
}, 1000)

</script>
<style lang='less' scoped></style>