<template>
  <div>
    <div style="display: inline-block;" class="block"><el-avatar :size="30" :src="circleUrl"></el-avatar>
      <div>欢迎{{username}}</div>
    </div>
    <el-col :span="20" :offset="2">
      <el-button @click="fAdd" type="primary" size="small">新增</el-button>
      <el-button @click="resetDateFilter">清除日期过滤器</el-button>
      <el-button @click="clearFilter">清除所有过滤器</el-button>
      <!-- <el-button @click="fgetAll" type="primary" size="small">获取所有数据</el-button> -->
      <el-table
        ref="filterTable"
        :data="tableData"
        :columns="columnsState"
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{ textAlign: 'center' }"
        row-key="id"
        lazy
        @expand-change="handleExpandChange"
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        style="width: 100%">
        <el-table-column
          fixed="left"
          prop="id"
          label="编号"
          width="100">
        </el-table-column>
        <el-table-column
          prop="date"
          label="日期"
          sortable
          width="150"
          column-key="date"
          :filters="[{text: '2021-05-21', value: '2021-05-21'}, {text: '2021-05-22', value: '2021-05-22'}, {text: '2021-05-23', value: '2021-05-23'}, {text: '2021-05-24', value: '2021-05-24'}]"
          :filter-method="filterHandler"
        >
        </el-table-column>
        <el-table-column
          prop="staplefood"
          label="主食"
          width="120">
        </el-table-column>
        <el-table-column
          prop="weight"
          label="分量"
          width="120">
        </el-table-column>
        <el-table-column
          prop="region"
          label="汤底"
          width="120">
        </el-table-column>
        <el-table-column
          prop="saozi"
          label="臊子"
          width="120">
        </el-table-column>
        <el-table-column
          prop="ingredients"
          label="配料"
          width="120">
        </el-table-column>
        <el-table-column
          prop="note"
          label="备注"
          width="180">
        </el-table-column>
        <el-table-column
          prop="type"
          fixed="right"
          label="操作"
          width="160">
          <template slot-scope="scope">
            <el-button @click="fPayfor(scope.row)" type="primary" size="small">付款</el-button>
            <el-button @click="fOut(scope.row)" type="primary" size="small">出餐</el-button>
          </template>
        </el-table-column>
        <!-- <el-table-column
          prop="address"
          label="地址"
          :formatter="formatter">
        </el-table-column> -->
        <el-table-column
          prop="tag"
          fixed="right"
          label="是否付款"
          width="100"
          :filters="[{ text: '已付款', value: '已付款' }, { text: '未付款', value: '未付款' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
              :type="calculateTag(scope.row.tag)"
              disable-transitions>{{scope.row.tag}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="out"
          fixed="right"
          label="是否出餐"
          width="100"
          :filters="[{ text: '已出餐', value: '已出餐' }, { text: '未出餐', value: '未出餐' }]"
          :filter-method="filterOut"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
              :type="calculateOut(scope.row.out)"
              disable-transitions>{{scope.row.out}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="编辑"
          width="250">
          <template slot-scope="scope">
            <div>
              <el-button type="success" size="small" @click="fChange(scope.row)">修改</el-button>
              <!-- <el-button v-html="addHtml"></el-button> -->
              <el-button type="success" size="small" @click="fSubAdd(scope.row)">子新增</el-button>
              <el-button type="success" size="small" @click="fFinish(scope.row)">完成</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[8, 16, 24, 32]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-col>

    <!-- 新增 -->
    <el-dialog
      title="新增"
      :visible.sync="centerDialogVisible"
      width="55%"
      center
      :before-close="fChangeDialogFormVisibleStateToTrue">
      <el-form :model="form" label-width="100px" class="demo-ruleForm">
        <el-descriptions style="font-size: 16px;" class="margin-top" :column="2" border>
          <!-- <template slot="extra">
            <el-button @click="dialogFormVisible = true" type="primary" size="small">操作</el-button>
          </template> -->
          <el-descriptions-item :span="1">
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-user"></i>
                操作员
              </div>
            </template>
            <el-input disabled class="no_input_border" v-model="form.user"></el-input>
          </el-descriptions-item>
          <el-descriptions-item :span="1">
            <template slot="label">
              <div style="width: 90px;">
                <i class="el-icon-star-on"></i>
                主食
              </div>
            </template>
            <el-select v-model="form.staplefood" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in foodOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>

          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                分量
              </div>
            </template>
            <el-select v-model="form.weight" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in weightOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                汤底
              </div>
            </template>
            <el-select v-model="form.region" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in regionOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                臊子
              </div>
            </template>
            <el-select v-model="form.saozi" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in saoziOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                配料
              </div>
            </template>
            <el-select v-model="form.ingredients" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in ingredientsOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              备注
            </template>
            <el-input v-model="form.note"></el-input>
            <!-- <el-tag size="small">学校</el-tag> -->
          </el-descriptions-item>
        </el-descriptions>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fChangeDialogFormVisibleStateToTrue">取 消</el-button>
        <el-button type="primary" @keyup.enter.native="fSubmit" @click="fSubmit('submitForm')">提 交</el-button>
      </div>
    </el-dialog>

    <!-- 修改 -->
    <el-dialog
      title="修改"
      :visible.sync="changeDialogVisible"
      width="55%"
      center
      :before-close="fChangeState">
      <el-form :model="changeForm" label-width="100px" class="demo-ruleForm">
        <el-descriptions style="font-size: 16px;" class="margin-top" :column="2" border>
          <!-- <template slot="extra">
            <el-button @click="dialogFormVisible = true" type="primary" size="small">操作</el-button>
          </template> -->
          <el-descriptions-item :span="1">
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-user"></i>
                操作员
              </div>
            </template>
            <el-input disabled class="no_input_border" v-model="changeForm.user"></el-input>
          </el-descriptions-item>
          <el-descriptions-item :span="1">
            <template slot="label">
              <div style="width: 90px;">
                <i class="el-icon-star-on"></i>
                主食
              </div>
            </template>
            <el-select v-model="changeForm.staplefood" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in foodOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>

          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                分量
              </div>
            </template>
            <el-select v-model="changeForm.weight" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in weightOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                汤底
              </div>
            </template>
            <el-select v-model="changeForm.region" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in regionOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                臊子
              </div>
            </template>
            <el-select v-model="changeForm.saozi" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in saoziOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <div style="width: 120px;">
                <i class="el-icon-star-on"></i>
                配料
              </div>
            </template>
            <el-select v-model="changeForm.ingredients" autocomplete="on" placeholder="请选择">
              <el-option
                v-for="item in ingredientsOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              备注
            </template>
            <el-input v-model="changeForm.note"></el-input>
            <!-- <el-tag size="small">学校</el-tag> -->
          </el-descriptions-item>
        </el-descriptions>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fChangeState">取 消</el-button>
        <el-button type="primary" @keyup.enter.native="fChangeSubmit" @click="fChangeSubmit">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { postMapping, formatDate, getMapping } from '../utils/requestUtils'
export default {
  data () {
    return {
      columnsState: [
        {
          title: "编辑",
          render: (h, params) => {
            return h('div', [
              h('el-button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  display: (params.row.status == '1') ? "inline-block": "none"
                }
              }, '修改'),
              h('el-button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  display: (params.row.status == '1') ? "inline-block": "none"
                }
              }, '子新增'),
              h('el-button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  display: (params.row.status == '1') ? "inline-block": "none"
                }
              }, '完成')
            ])
          }
        }
      ],
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      username: '',
      payDisabled: false,
      outDisabled: false,
      currentPage: 1,
      pageSize: 8,
      total: 0,
      centerDialogVisible: false,
      changeDialogVisible: false,
      tableList: '',
      tableData: [],
      form: {
        user: '',
        staplefood: '',
        weight: '',
        region: '',
        saozi: '',
        ingredients: '',
        note: '',
        tag: ''
      },
      changeForm: {
        id: '',
        user: '',
        staplefood: '',
        weight: '',
        region: '',
        saozi: '',
        ingredients: '',
        note: '',
        tag: ''
      },
      // 主食选择框
      foodOptions: [{
        value: '米粉',
        label: '米粉'
      }, {
        value: '面',
        label: '面'
      }, {
        value: '抄手',
        label: '抄手'
      }, {
        value: '饺子',
        label: '饺子'
      }],
      // 分量选择框
      weightOptions: [{
        value: '一两',
        label: '一两'
      }, {
        value: '二两',
        label: '二两'
      }, {
        value: '三两',
        label: '三两'
      }],
      // 汤底选择框
      regionOptions: [{
        value: '清汤',
        label: '清汤'
      }, {
        value: '清红汤',
        label: '清红汤'
      }, {
        value: '红汤',
        label: '红汤'
      }],
      // 臊子选择框
      saoziOptions: [{
        value: '牛肉',
        label: '牛肉'
      }, {
        value: '肥肠',
        label: '肥肠'
      }, {
        value: '笋子',
        label: '笋子'
      }, {
        value: '鸡杂',
        label: '鸡杂'
      }, {
        value: '牛杂',
        label: '牛杂'
      }, {
        value: '炸酱',
        label: '炸酱'
      }, {
        value: '排骨',
        label: '排骨'
      }, {
        value: '海带豌豆',
        label: '海带豌豆'
      }, {
        value: '鸡汤',
        label: '鸡汤'
      }],
      // 配料选择框
      ingredientsOptions: [{
        value: '煎蛋',
        label: '煎蛋'
      }, {
        value: '卤蛋',
        label: '卤蛋'
      }, {
        value: '鸡腿',
        label: '鸡腿'
      }, {
        value: '咸鸭蛋',
        label: '咸鸭蛋'
      }, {
        value: '豌豆',
        label: '豌豆'
      }, {
        value: '海带',
        label: '海带'
      }, {
        value: '凉糕',
        label: '凉糕'
      }],
      insertPath: '',
      getAllPath: '',
      getTotalLengthPath: '',
      payforPath: '',
      finishPath: '',
      updatePath: '',
      outPath: '',
      fatherIdPath: '',
      subId: 0,
      hasLoad: false,
      currentLoadTreeData: '',
      resolveObj: '',
      intervalIsOn: false,
      expandedInterval: '',
      addHtml: "<el-button type='success' size='small' @click='fSubAdd(scope.row)'>子新增</el-button>"
    }
  },
  mounted () {
    this.username = this.$cookieStore.getCookie('username')
    const pathJson = require('../../static/path')
    const { basePath, insertPath, getAllPath, getTotalLengthPath, payforPath, finishPath, updatePath, outPath, fatherIdPath } = pathJson
    this.insertPath = basePath + insertPath
    this.getAllPath = basePath + getAllPath
    this.getTotalLengthPath = basePath + getTotalLengthPath
    this.payforPath = basePath + payforPath
    this.finishPath = basePath + finishPath
    this.updatePath = basePath + updatePath
    this.outPath = basePath + outPath
    this.fatherIdPath = basePath + fatherIdPath
    setInterval(this.fgetAll, 5000)
    setInterval(this.fgetTotalLength, 5000)
  },
  methods: {
    // addHtml () {
    //   return '<el-button type="success" size="small" @click="fSubAdd(scope.row)">子新增</el-button>'
    // },
    async load(tree, treeNode, resolve) {
      this.hasLoad = true
      this.currentLoadTreeData = tree
      this.resolveObj = resolve
      await this.getReadOnlyDetail(tree, resolve)
      // console.log('tree', tree)
      // console.log('treeNode', treeNode)
    },
    async getReadOnlyDetail (tree, resolve) {
      setTimeout(async () => {
        var json = {
          fatherid: tree.id
        }
        var list = ''
        var LIST = []
        // console.log('dddd')
        var result = postMapping(this.fatherIdPath, json)
        // console.log('result', result.then())
        await result.then(res => {
          list = res
          // console.log('list', res)
        })
        list.forEach(item => {
          var elementlist = {
            id: 0,
            date: '',
            staplefood: '',
            weight: '',
            region: '',
            saozi: '',
            ingredients: '',
            note: '',
            type: '',
            tag: '',
            out: '',
            status: '0'
          }
          // 构造元素
          // console.log('item', item)
          elementlist.id = parseInt(item.id)
          elementlist.ingredients = item.ingredients
          elementlist.note = item.note
          elementlist.region = item.region
          elementlist.saozi = item.saozi
          elementlist.staplefood = item.staplefood
          elementlist.date = item.time
          elementlist.weight = item.weight
          elementlist.tag = item.tag
          elementlist.out = item.isout
          LIST.push(elementlist)
        })
        resolve(LIST)
      }, 1000)
    },
    intervaltest () {
      // console.log('执行interval')
      if (this.hasLoad) {
        this.hasLoad = false
      } else {
        this.load(this.currentLoadTreeData, '', this.resolveObj)
      }
    },
    handleExpandChange (row, expended) {
      if (expended) {
        // console.log('expended', expended)
        // console.log('展开')
        if (this.intervalIsOn === false) {
          console.log('开启定时器')
          this.intervalIsOn = true
          this.expandedInterval = setInterval(this.intervaltest, 3000)
        }
      } else {
        // console.log('关闭')
        // console.log('执行清除interval')
        // console.log('expandedInterval12', this.expandedInterval)
        // this.hasLoad = false
        this.intervalIsOn = false
        clearInterval(this.expandedInterval)
      }
    },
    fChange (row) {
      this.changeDialogVisible = true
      this.changeForm.user = this.username
      this.changeForm.staplefood = row.staplefood
      this.changeForm.weight = row.weight
      this.changeForm.region = row.region
      this.changeForm.ingredients = row.ingredients
      this.changeForm.note = row.note
      this.changeForm.saozi = row.saozi
      this.changeForm.id = row.id
      this.changeForm.tag = row.tag
    },
    handleSizeChange (num) {
      this.pageSize = num
    },
    handleCurrentChange (num) {
      this.currentPage = num
    },
    async fgetTotalLength () {
      var result = getMapping(this.getTotalLengthPath, '')
      // console.log('result', result.then())
      await result.then(res => {
        this.total = res
      })
    },
    resetDateFilter () {
      this.$refs.filterTable.clearFilter('date')
    },
    clearFilter () {
      this.$refs.filterTable.clearFilter()
    },
    filterTag (value, row) {
      return row.tag === value
    },
    filterOut (value, row) {
      return row.out === value
    },
    Arrivefilter (value, row) {
      return row.type === value
    },
    filterHandler (value, row, column) {
      const property = column['property']
      // console.log(property)
      return row[property] === value
    },
    fAdd () {
      this.centerDialogVisible = true
      this.form.user = this.username
    },
    fSubAdd (row) {
      this.centerDialogVisible = true
      this.form.user = this.username
      this.subId = row.id
    },
    async fgetAll () {
      var json = {
        page: this.currentPage,
        num: this.pageSize
      }
      // map.set('page', 1)
      // map.set('num', 5)
      var result = postMapping(this.getAllPath, json)
      // console.log('result', result.then())
      await result.then(res => {
        this.tableList = res
      })
      this.tableData = []
      this.tableList.forEach(item => {
        var elementlist = {
          id: 0,
          date: '',
          staplefood: '',
          weight: '',
          region: '',
          saozi: '',
          ingredients: '',
          note: '',
          type: '',
          tag: '',
          out: '',
          hasChildren: '',
          status: '1'
        }
        // 构造元素
        // console.log('item', item)
        elementlist.id = parseInt(item.id)
        elementlist.ingredients = item.ingredients
        elementlist.note = item.note
        elementlist.region = item.region
        elementlist.saozi = item.saozi
        elementlist.staplefood = item.staplefood
        elementlist.date = item.time
        elementlist.weight = item.weight
        elementlist.tag = item.tag
        elementlist.out = item.isout
        if (item.father === '-1') {
          elementlist.hasChildren = true
        } else {
          elementlist.hasChildren = false
        }
        this.tableData.push(elementlist)
        // elementlist.clear()
      })
      this.tableData.sort(function (p1, p2) {
        return p1.id - p2.id
      })
      this.hasLoad = false
    },
    calculateTag (tag) {
      if (tag === '已支付') {
        return 'success'
      } else {
        return 'danger'
      }
    },
    calculateOut (out) {
      if (out === '已出餐') {
        return 'success'
      } else {
        return 'danger'
      }
    },
    fChangeDialogFormVisibleStateToTrue () {
      this.form.staplefood = ''
      this.form.saozi = ''
      this.form.ingredients = ''
      this.form.region = ''
      this.form.weight = ''
      this.form.note = ''
      this.centerDialogVisible = false
    },
    fChangeState () {
      this.changeForm.staplefood = ''
      this.changeForm.saozi = ''
      this.changeForm.ingredients = ''
      this.changeForm.region = ''
      this.changeForm.weight = ''
      this.changeForm.note = ''
      this.changeDialogVisible = false
    },
    async fSubmit () {
      // 提交
      var timeStamp = formatDate(new Date().getTime())
      var json = {
        username: this.username,
        time: timeStamp,
        staplefood: this.form.staplefood,
        weight: this.form.weight,
        region: this.form.region,
        saozi: this.form.saozi,
        ingredients: this.form.ingredients,
        note: this.form.note,
        tag: this.form.tag,
        id: this.subId
      }
      var logstate
      var result = postMapping(this.insertPath, json)
      // console.log('result', result.then())
      await result.then(res => {
        // console.log("zixinzeng", res)
        if (res.flag = "子新增"){
          // console.log("jinru")
          // this.load()
        }
        this.subId = 0
      })
      this.form.staplefood = ''
      this.form.saozi = ''
      this.form.ingredients = ''
      this.form.region = ''
      this.form.weight = ''
      this.form.note = ''
      this.centerDialogVisible = false
      this.fgetTotalLength()
    },
    async fChangeSubmit () {
      // 修改
      var json = {
        id: this.changeForm.id,
        staplefood: this.changeForm.staplefood,
        weight: this.changeForm.weight,
        region: this.changeForm.region,
        saozi: this.changeForm.saozi,
        ingredients: this.changeForm.ingredients,
        note: this.changeForm.note,
        tag: this.changeForm.tag
      }
      var result = postMapping(this.updatePath, json)
      // console.log('result', result.then())
      await result.then(res => {

      })
      this.changeForm.staplefood = ''
      this.changeForm.saozi = ''
      this.changeForm.ingredients = ''
      this.changeForm.region = ''
      this.changeForm.weight = ''
      this.changeForm.note = ''
      this.changeDialogVisible = false
    },
    async fPayfor (row) {
      // row.tag = '已支付'
      var json = {
        id: row.id
      }
      var result = postMapping(this.payforPath, json)
      // console.log('result', result.then())
      await result.then(res => {
        this.payDisabled = true
      })
    },
    async fOut (row) {
      var json = {
        id: row.id
      }
      var result = postMapping(this.outPath, json)
      // console.log('result', result.then())
      await result.then(res => {
        this.outDisabled = true
      })
    },
    async fFinish (row) {
      var json = {
        id: row.id
      }
      var result = postMapping(this.finishPath, json)
      // console.log('result', result.then())
      await result.then(res => {

      })
    }
  }
}
</script>

<style lang="css" scoped="scoped">
  .el-icon-star-on{
    color: red;
    font-size: 5px;
  }
</style>
