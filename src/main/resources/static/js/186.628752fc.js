"use strict";(self["webpackChunkgramvue2"]=self["webpackChunkgramvue2"]||[]).push([[186],{6186:function(e,t,n){n.r(t),n.d(t,{default:function(){return u}});var l=function(){var e=this,t=e._self._c;return e.cellData.visible?t("li",{staticClass:"kalendar-cell",class:{selected:e.selected,"is-an-hour":e.isAnHour(e.index),"has-events":e.cell_events&&e.cell_events.length>0,"being-created":!!e.being_created||e.hasPopups},style:`\n  height: ${e.kalendar_options.cell_height}px;\n`,on:{mouseover:function(t){return t.target!==t.currentTarget?null:e.mouseMove()},mousedown:function(t){return t.target!==t.currentTarget?null:e.mouseDown()},mouseup:function(t){return e.mouseUp()}}},[e.cell_events&&e.cell_events.length?t("div",e._l(e.cell_events,(function(n,l){return t("KalendarEvent",{key:l,style:"z-index: 10",attrs:{event:n,total:e.cell_events.length,index:l,overlaps:e.overlapValue}})})),1):e._e()]):e._e()},s=[],r=n(8843),a={props:["creator","index","cellData","constructedEvents","temporaryEvent"],inject:["kalendar_options"],components:{KalendarEvent:()=>n.e(202).then(n.bind(n,7202))},computed:{cell_events(){let e=[];return this.completed_events&&(e=e.concat(this.completed_events)),this.being_created&&(e=e.concat(this.being_created)),e},completed_events(){return this.constructedEvents&&this.constructedEvents.hasOwnProperty(this.cellData.value)&&this.constructedEvents[this.cellData.value]},being_created(){return this.temporaryEvent&&this.temporaryEvent.start.value===this.cellData.value&&this.temporaryEvent},overlappingEvents(){return!this.constructedEvents||this.cell_events.length<1?[]:Object.values(this.constructedEvents).flat().filter((e=>{let t=new Date(this.cellData.value),n=new Date(e.start.value),l=new Date(e.end.value);return n<t&&l>t}))},overlapValue(){let e=this.overlappingEvents.length;return e>2?2:e},selected(){return this.cell_events&&this.cell_events.length>0},hasPopups(){return this.selected&&!!this.cell_events.find((e=>"popup-initiated"===e.status))}},methods:{mouseDown(){if(this.creator.creating)return void this.mouseUp();let{read_only:e,overlap:t,past_event_creation:n}=this.kalendar_options;if(e)return;if(!1===n){let e=(0,r.BV)(new Date);if(new Date(e)>new Date(this.cellData.value))return void this.mouseUp()}if(console.log("Cell events:",this.cell_events.length),!t&&this.cell_events.length>0)return;this.$kalendar.closePopups();let l={creating:!0,original_starting_cell:(0,r.kI)(this.cellData),starting_cell:(0,r.kI)(this.cellData),current_cell:(0,r.kI)(this.cellData),ending_cell:(0,r.kI)(this.cellData)};this.$emit("select",l)},mouseMove(){let{read_only:e,overlap:t}=this.kalendar_options;if(e)return;if(this.creator&&!this.creator.creating)return;let{starting_cell:n,original_starting_cell:l,creating:s}=this.creator,r=this.cellData.index>=n.index&&n.index===l.index;if(s){let e={...this.creator,current_cell:this.cellData,ending_cell:this.cellData,direction:r?"normal":"reverse"};this.$emit("select",e)}},mouseUp(){this.kalendar_options.read_only||this.creator.creating&&this.$emit("initiatePopup")},resetCreator(){this.$emit("reset")},isAnHour(e){return!!this.kalendar_options.hourlySelection||(e+1)%6===0}}},i=a,c=n(1001),o=(0,c.Z)(i,l,s,!1,null,null,null),u=o.exports}}]);
//# sourceMappingURL=186.628752fc.js.map