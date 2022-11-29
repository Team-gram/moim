"use strict";(self["webpackChunkgramvue2"]=self["webpackChunkgramvue2"]||[]).push([[15],{1015:function(e,t,s){s.r(t),s.d(t,{default:function(){return D}});var a=function(){var e=this,t=e._self._c;return t("div",{staticClass:"calendar-wrap",style:`--space-between-cols: ${e.colsSpace}`},[t("div",{staticClass:"sticky-top"},[t("ul",{staticClass:"days"},e._l(e.days||[],(function({value:s},a){return t("li",{key:a,staticClass:"day-indicator",class:{"is-before":e.isDayBefore(s)}},[t("div",[t("span",{staticClass:"letters-date"},[e._v(e._s(e.kalendar_options.formatDayTitle(s)[0]))]),t("span",{staticClass:"number-date",class:{Dday:e._isToday(s),aDay:!e._isToday(s)}},[e._v(e._s(e.kalendar_options.formatDayTitle(s)[1]))])])])})),0),t("ul",{staticClass:"all-day"},[t("span",[e._v("All Day")]),e._l(e.days||[],(function(s,a){return t("li",{key:a,class:{"all-today":e._isToday(s.value),"is-all-day":!1},style:`height:${e.kalendar_options.cell_height+5}px`})}))],2)]),e._e(),e.hours?t("div",{staticClass:"blocks"},[t("div",{staticClass:"calendar-blocks"},[t("ul",{staticClass:"hours"},e._l(e.hoursVisible,(function(s,a){return t("li",{key:a,staticClass:"hour-row-identifier",style:`height:${e.hourHeight}px`},[t("span",[e._v(e._s(e.kalendar_options.formatLeftHours(s.value)))])])})),0),t("div",{directives:[{name:"show",rawName:"v-show",value:"material_design"!==e.kalendar_options.style,expression:"kalendar_options.style !== 'material_design'"}],staticClass:"hour-indicator-line",style:`top:${e.passedTime.distance}px`},[t("span",{staticClass:"time-value"},[e._v(e._s(e.passedTime.value))]),t("span",{staticClass:"line"})]),e._l(e.days,(function(s,a){return t("kalendar-days",{key:s.value.slice(0,10),ref:s.value.slice(0,10),refInFor:!0,staticClass:"building-blocks",class:`day-${a+1}`,attrs:{day:s,"passed-time":e.passedTime.distance}})}))],2)]):e._e()])},n=[],i=(s(7658),function(){var e=this,t=e._self._c;return t("ul",{ref:e.day.value+"-reference",staticClass:"kalendar-day",class:{"is-weekend":e.isWeekend,"is-today":e.isToday,creating:e.creator.creating||"popup-initiated"===e.creator.status},staticStyle:{position:"relative"}},[e.isToday?t("div",{ref:"nowIndicator",class:"material_design"===e.kalendar_options.style?"hour-indicator-line":"hour-indicator-tooltip",style:`top:${e.passedTime}px`}):e._e(),e._l(e.day_cells,(function(s,a){return t("kalendar-cell",{key:`cell-${a}`,attrs:{"constructed-events":e.day_events,creator:e.creator,"cell-data":s,index:a,"temporary-event":e.temporary_event},on:{select:e.updateCreator,reset:function(t){return e.resetEvents()},initiatePopup:function(t){return e.initiatePopup()}}})}))],2)}),l=[],r=s(8843);function o(e){let t=0,s={};e.addEventListener("message",(e=>a(e)));const a=e=>{const t=e.data;if(!Array.isArray(t)||t.length<2)return;const a=t[0],n=t[1],i=t[2],l=s[a];l&&(delete s[a],l(n,i))};return{initiateWorker(e){},postMessage(n){const i=t++,l=[i,n];return new Promise(((t,n)=>{if(s[i]=(e,s)=>{if(e)return n(new Error(e.message));t(s)},"undefined"!==typeof e.controller){const t=new MessageChannel;t.port1.onmessage=function(e){a(e)},e.controller.postMessage(l,[t.port2])}else e.postMessage(l)}))}}}function d(){return new Worker(s.p+"js/operations.worker.41c13f90.worker.js")}const u=new d,c=o(u),h=(e="message",t)=>c.postMessage({type:e,data:t});var p={send:h},_={props:["day","passedTime"],created(){this.renderDay()},components:{kalendarCell:()=>s.e(186).then(s.bind(s,6186))},provide(){return{kalendarAddEvent:this.addEvent,kalendarClearPopups:this.clearCreatingLeftovers}},inject:["kalendar_options"],mounted(){this.kalendar_options.scrollToNow&&this.isToday&&this.scrollView()},computed:{isWeekend(){return(0,r.QD)(this.day.value)},isToday(){return(0,r.zk)(this.day.value)}},data:()=>({creator:{creating:!1,starting_cell:null,original_starting_cell:null,current_cell:null,ending_cell:null,status:null},temporary_event:null,day_cells:[],day_events:null}),methods:{renderDay(){p.send("getDayCells",{day:this.day.value,hourOptions:{start_hour:this.kalendar_options.day_starts_at,end_hour:this.kalendar_options.day_ends_at},hourlySelection:this.kalendar_options.hourlySelection}).then((e=>(this.day_cells=e,this.getDayEvents(this.$kalendar.getEvents()))))},addEvent(e){let t=this.checkEventValidity(e);if(null!==t)return Promise.reject(t);let{from:s,to:a}=e;return s=(0,r.BV)(s),a=(0,r.BV)(a),p.send("constructNewEvent",{event:{...e,from:s,to:a}}).then((t=>{let{key:s}=t;this.day_events.hasOwnProperty(s)?this.day_events[s].push(t):this.$set(this.day_events,s,[t]);let a=this.$kalendar.getEvents();console.log("Adding event to kalendar",e),a.push({...e,id:t.id}),this.$kalendar.updateEvents(a)}))},removeEvent(e){let t=this.$kalendar.getEvents(),s=t.findIndex((t=>t.id===e.id));if(s<0)return;t.splice(s,1);let a=this.day_events[e.key].findIndex((t=>t.id===e.id));return this.day_events[e.key].splice(a,1),this.$kalendar.updateEvents(t),Promise.resolve()},checkEventValidity(e){let{from:t,to:s}=e;return t&&s?null:"No dates were provided in the payload"},getDayEvents(e){let t=e.map((e=>(0,r.kI)(e)));return p.send("constructDayEvents",{events:t,day:this.day.value}).then((e=>{this.day_events=e}))},clearCreatingLeftovers(){for(let e in this.day_events){let t=this.day_events[e].some((e=>"popup-initiated"===e.status||"creating"===e.status));if(t){let t=this.day_events[e].filter((e=>"completed"===e.status));this.$set(this.day_events,e,t),0===t.length&&delete this.day_events[e]}}this.resetEvents()},resetEvents(){(this.creator.creating||null!==this.creator.status)&&(this.creator={creating:!1,starting_cell:null,original_starting_cell:null,current_cell:null,ending_cell:null,status:null,temporary_id:null},this.temporary_event=null)},updateCreator(e){if(this.creator={...this.validateSelection(e),status:"creating"},!1===this.kalendar_options.overlap&&Object.keys(this.day_events).length>0){let t=this.overlapPolice(e);if(t)return this.creator=this.validateSelection(t),this.selectCell(),void this.initiatePopup()}this.selectCell()},validateSelection(e){let{original_starting_cell:t,starting_cell:s,current_cell:a}=e;return"reverse"===e.direction&&t.index>a.index?{...e,starting_cell:a,ending_cell:t}:e},selectCell(){if(!this.creator.creating)return;let{creating:e,ending_cell:t,current_cell:s,starting_cell:a,original_starting_cell:n}=this.creator,i=t.index+1;t=this.day_cells[i];const l=new Date(t.value)-new Date(a.value),r=Math.floor(l%864e5/36e5),o=Math.round(l%864e5%36e5/6e4);let d=new Date(a.value),u=new Date(t.value),c=o+r*(this.kalendar_options.hourlySelection?10:60);this.temporary_event={start:{masked_value:d.toISOString(),value:d.toISOString(),rounded:!1,round_offset:null},end:{masked_value:u.toISOString(),value:u.toISOString(),rounded:!1,round_offset:null},distance:c,status:"creating"}},initiatePopup(){if(this.creating&&"creating"!==this.creator.status)return;this.creator={...this.creator,status:"popup-initiated",creating:!1};let{ending_cell:e,current_cell:t,starting_cell:s,original_starting_cell:a}=this.creator,n=e.index+1;e=this.day_cells[n];const i=new Date(e.value)-new Date(s.value),l=Math.floor(i%864e5/36e5),r=Math.round(i%864e5%36e5/6e4);let o=new Date(s.value),d=new Date(e.value),u={start:{masked_value:o.toISOString(),value:o.toISOString(),rounded:!1,round_offset:null},end:{masked_value:d.toISOString(),value:d.toISOString(),rounded:!1,round_offset:null},distance:r+l*(this.kalendar_options.hourlySelection?10:60),status:"popup-initiated"},c=this.day_events[s.value];c||(c=[]),c.push(u),this.$set(this.day_events,s.value,c),this.temporary_event=null},overlapPolice(e){if(!e.current_cell)return;let t=Object.keys(this.day_events).map((e=>this.day_events[e])).flat().filter((t=>{let s=new Date(e.starting_cell.value),a=new Date(e.ending_cell.value),n=new Date(t.start.value),i=new Date(t.end.value);return a>n&&a<i||s<n&&a>n}));if(!t||0===t.length)return;let s=e;if("reverse"===e.direction){let e=t[0].end,a=this.day_cells.find((t=>t.value===e.masked_value)),n=this.day_cells[a.index];s.starting_cell={value:n.value,index:n.index},s.current_cell={value:n.value,index:n.index}}else{let e=t[0].start,a=this.day_cells.find((t=>t.value===e.masked_value)),n=this.day_cells[a.index-1];s.ending_cell={value:n.value,index:n.index}}return s},scrollView(){let e=this.$refs.nowIndicator.offsetTop;console.log("Scrolling to :",e),setTimeout((()=>{window.scroll({top:e,left:0,behavior:"smooth"})}),500)}}},v=_,y=s(1001),g=(0,y.Z)(v,i,l,!1,null,null,null),f=g.exports,k={props:{current_day:{required:!0,type:String,validator:e=>!isNaN(Date.parse(e))}},components:{KalendarDays:f},created(){this.addHelperMethods(),setInterval((()=>this.kalendar_options.now=new Date),6e4),this.constructWeek()},inject:["kalendar_options","kalendar_events"],data:()=>({hours:null,days:[]}),computed:{hoursVisible(){return this.hours?this.hours.filter((e=>!!e.visible)):[]},colsSpace(){return"flat_design"===this.kalendar_options.style?"5px":"0px"},hourHeight(){return(this.kalendar_options.hourlySelection?1:6)*this.kalendar_options.cell_height},passedTime(){let{day_starts_at:e,day_ends_at:t,now:s}=this.kalendar_options,a=(0,r.BV)(s),n=`${a.split("T")[0]}T${(e+"").padStart(2,"0")}:00:00.000Z`,i=`${a.split("T")[0]}T${(t+"").padStart(2,"0")}:00:00.000Z`,l=new Date(a);if(new Date(i)<l||l<new Date(n))return null;let o=(l-new Date(n))/1e3/60;return{distance:o,time:a}}},methods:{_isToday(e){return(0,r.zk)(e)},updateAppointments({id:e,data:t}){this.$emit("update",{id:e,data:t})},deleteAppointment(e){this.$emit("delete",{id:e})},clearAppointments(){this.$emit("clear")},isDayBefore(e){let t=new Date(this.kalendar_options.now),s=(0,r.BV)(t.toISOString());return(0,r.RR)(e,(0,r.BW)(s))},constructWeek(){const e=this.current_day.slice(0,10),{hide_dates:t,hide_days:s,view_type:a}=this.kalendar_options;return Promise.all([p.send("getDays",{day:e,options:{hide_dates:t,hide_days:s,view_type:a}}).then((e=>{this.days=e})),p.send("getHours",{hourOptions:{start_hour:this.kalendar_options.day_starts_at,end_hour:this.kalendar_options.day_ends_at}}).then((e=>{this.hours=e}))])},addHelperMethods(){this.$kalendar.buildWeek=()=>{this.constructWeek()},this.$kalendar.addNewEvent=e=>{if(!e)return Promise.reject("No payload");let{from:t,to:s}=e;"000Z"===t.slice(-4)&&(e.from=(0,r.V3)(t)),"000Z"===s.slice(-4)&&(e.to=(0,r.V3)(s));let a=e.from.slice(0,10);const n=this.$refs[a];if(n&&n[0])n[0].addEvent(e);else{let t=this.$kalendar.getEvents();t.push(e),this.$kalendar.updateEvents(t)}},this.$kalendar.removeEvent=e=>{let{day:t,key:s,id:a}=e;if(t.length>10&&(t=t.slice(0,10)),console.log("Options:",e),!t)return Promise.reject("Day wasn't provided");if(!a)return Promise.reject("No ID was provided");if(!s)return Promise.reject("No key was provided in the object");let n=t;this.$refs[n][0].removeEvent({id:a,key:s})},this.$kalendar.closePopups=()=>{let e=this.days.map((e=>e.value.slice(0,10)));e.forEach((e=>{this.$refs[e][0].clearCreatingLeftovers()}))}}}},m=k,w=(0,y.Z)(m,a,n,!1,null,null,null),D=w.exports}}]);
//# sourceMappingURL=15.44cf6ea7.js.map