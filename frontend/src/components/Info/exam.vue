<template>
<div>
  <h2>考试情况</h2>
  <div id="main" style="width:600px;height: 600px"></div>
</div>
</template>

<script>
import * as echarts from 'echarts';
import { TooltipComponent, LegendComponent }
  from 'echarts/components';
import { PieChart } from 'echarts/charts';
import { LabelLayout } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

echarts.use([
  TooltipComponent,
  LegendComponent,
  PieChart,
  CanvasRenderer,
  LabelLayout
]);
export default {
  name:"exam",
  mounted() {
    var chartDom =document.getElementById('main');
    var myChart =echarts.init(chartDom);
    var option;
    option={
      tooltip:{
        trigger:'item'
      },
      legend:{
        top:'5%',
        left:'center',
      },
      series:[
        {
          name:'得分情况',
          type:'pie',
          radius:['40%','70%'],
          avoidLabelOverlap:false,
          label:{
            show:false,
            position:'center'
          },
          emphasis:{
            label:{
              show:true,
              fontSize:'40',
              fontWeight:'bold'
            }
          },
          labelLine:{
            show:false
          },
          data:[
            {value:22,name:'单选'},
            {value:20,name:'多选'},
            {value:18,name:'判断'},
            {value:30,name:'简答'},
          ]
        }
      ]
    }
    option && myChart.setOption(option);
  }
}
</script>

<style scoped>

</style>