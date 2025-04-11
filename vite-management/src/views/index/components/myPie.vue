<!--  -->
<template>
<!--
 <input v-model="model1"  />-->
 <span @click="ff(111)">1111111111111</span>
 <br>


  <el-radio-group v-model="selectLabel" @change="initPie">
    <el-radio-button label="销量">销量</el-radio-button>
    <el-radio-button label="销售额">销售额</el-radio-button>
  </el-radio-group>
  <el-select v-model="selectDay" placeholder="Select" @change="initPie">
    <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value" />
  </el-select>
  <div id="pie" style="width: 100%;height: 500px;"></div>
</template>

<script lang='ts' setup>
import { reactive, toRefs, ref, onMounted, watch } from 'vue'
import * as echarts from 'echarts';

const model1 = defineModel("count1", {
  type: String,
  default: "aaa",
});
const ff=(v:any)=>{
  model1.value=v;
}


const props = defineProps<{
  data: {seriesName:string; xAxisData: string[];seriesData:number[]}[]
  //data: {}[]
  a:string
}>();
const state = reactive({
  selectLabel: '销量',
  selectDay: '今天',
  options: [
    { name: '今天', value: 'today' },
    { name: '昨天', value: 'yesterday' },
    { name: '前3天', value: 'three_days' },
    { name: '前7天', value: 'seven_days' },
  ]
});
const { selectDay, selectLabel, options } = toRefs(state);

const initPie = () => {

  console.log(selectDay.value);
  console.log(selectLabel.value);

  let title = selectLabel.value+'          '+selectDay.value;


  let xAxisData:string[];
  let seriesName:string;
  let seriesData:number[];

  for(let val of props.data) {
    seriesName = val['seriesName'];
    xAxisData = val['xAxisData'];
    seriesData = val['seriesData'];
  }


  const myChart = echarts.init(document.getElementById('pie'));
  // 绘制图表
  myChart.setOption({
    title: {
      text: title
    },
    tooltip: {},
    xAxis: {
      data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
    },
    yAxis: {},
    series: [
      {
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
      }
    ]
  });
}

watch(() => props.data, () => {
  console.log('------watch---------');

  initPie();
});
watch(() => props.a, () => {
  console.log('------watch-a--------'+props.a);

});
</script>
<style lang='less' scoped></style>