<template>
    <div>
      <h2>Answer Data Chart</h2>
      <GChart
        type="PieChart"
        :options="options"
        :data="chartData"
      />
    </div>
  </template>
   
  <script>
  import { GChart } from "vue-google-charts/legacy";
  export default {
    name: "Chart",
    components: {
      GChart
    },
    data() {
      return {
        data: [[]],
        dataFormat: [
          ['Value', 'Count']
        ],
        answer: [],
        format: [],
        component: [],
        options: {
          width: 1100,
          height: 400,
          sliceVisibilityThreshold: 0
        }
      };
    },
    props:['answerCount'],
    computed: {
      chartData() {
        this.data = [
          ['Value', 'Count']
        ]
        for(let index=0; index<this.answerCount.length; index++) {
          let component = []
          component.push(this.answerCount[index].value)
          component.push(this.answerCount[index].count)
          this.data.push(component)
        }
        return this.data
      }
    }
  }
  </script>