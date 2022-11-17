(function(){"use strict";var e={6742:function(e,t,n){var r=n(6369),o=function(){var e=this,t=e._self._c;return t("div",[t("MoimHeader"),t("router-view")],1)},a=[],i=function(){var e=this,t=e._self._c;return t("body",[t("b-navbar",{staticClass:"shadow",attrs:{type:"dark",variant:"dark"}},[t("b-container",{staticClass:"navbar-container"},[t("b-row",{staticStyle:{width:"100vw","margin-top":"20px"},attrs:{"align-v":"center"}},[t("b-col",[t("b-navbar-brand",{staticStyle:{"margin-left":"10px"},attrs:{href:"#"}},[t("img",{attrs:{src:n(2983),width:"120"}})])],1),t("b-col",[t("b-button",{staticClass:"ml-auto login-button",staticStyle:{float:"right","margin-right":"10px"},attrs:{pill:"",variant:"light",id:"show-btn"},on:{click:function(t){return e.$bvModal.show("bv-modal-example")}}},[e._v("로그인 ")])],1),t("div",{staticClass:"w-100"}),t("b-col",[t("b-navbar-nav",{attrs:{fill:e.width<=600}},[t("b-nav-item",{attrs:{href:"#"}},[e._v("홈")]),t("b-nav-item",{attrs:{href:"#"}},[e._v("MY모임")]),t("b-nav-item",{attrs:{href:"#"}},[e._v("일정관리")]),t("b-nav-item",{attrs:{href:"#"}},[e._v("설정")])],1)],1)],1)],1),t("b-modal",{attrs:{id:"bv-modal-example",size:"sm","hide-footer":"","hide-header":"",centered:""}},[t("div",{staticClass:"d-block text-center",staticStyle:{"margin-bottom":"20px","margin-top":"20px"}},[t("h5",[e._v("로그인 | 회원가입")])]),t("div",{staticClass:"d-block text-center",staticStyle:{"margin-bottom":"20px"}},[t("img",{attrs:{src:n(2626)},on:{click:e.kakaoLogin}})])])],1)],1)},s=[],l={data(){return{width:0,height:0}},methods:{kakaoLogin(){const e={redirectUri:"http://localhost:8080/kakaologin"};window.Kakao.Auth.authorize(e)},handleResize(){this.width=window.innerWidth,this.height=window.innerHeight}},created(){this.width=window.innerWidth,this.height=window.innerHeight},mounted(){window.addEventListener("resize",this.handleResize)},beforeDestroy(){window.removeEventListener("resize",this.handleResize)}},u=l,c=n(1001),d=(0,c.Z)(u,i,s,!1,null,"398eb176",null),h=d.exports,f={name:"App",components:{MoimHeader:h},methods:{kakaoLogin(){const e={redirectUri:"http://localhost:8080/kakaologin"};window.Kakao.Auth.authorize(e)}}},v=f,p=(0,c.Z)(v,o,a,!1,null,null,null),g=p.exports,b=n(2631),m=function(){var e=this;e._self._c;return e._m(0)},k=[function(){var e=this,t=e._self._c;return t("div",{staticClass:"KakaoLogin"},[t("h1",[e._v("hello")])])}];const w={Authorization:"5c0e6f65ea3343ffb30b5c3732ca46ad","Content-type":"application/x-www-form-urlencoded;charset=utf-8"},A=async e=>{try{const t={grant_type:"authorization_code",client_id:"27769c331d08ceb2033e090a83e1e212",redirect_uri:"http://localhost:8080/kakaologin",code:e},n=Object.keys(t).map((e=>encodeURIComponent(e)+"="+encodeURIComponent(t[e]))).join("&");await fetch("https://kauth.kakao.com/oauth/token",{method:"POST",headers:w,body:n}).then((e=>e.json())).then((e=>{window.Kakao.Auth.setAccessToken(e.access_token)}))}catch(t){console.error(t)}},C=async()=>{let e="";return await window.Kakao.API.request({url:"/v2/user/me",success:function(t){e=t},fail:function(e){console.log(e)}}),e};var y={name:"KakaoLogin",created(){this.$route.query.code&&this.setKakaoToken()},methods:{async setKakaoToken(){A(this.$route.query.code);let e=await C();this.axios.get("https://2080c2c3-dc1f-48fa-8d27-db3471cbce2a.mock.pstmn.io/validate",{params:{id:e.id}}).then((e=>{console.log(e)})),this.$router.replace("/register-data"),console.log(e.id),this.$router.replace("/main")}}},x=y,T=(0,c.Z)(x,m,k,!1,null,null,null),j=T.exports,O=function(){var e=this,t=e._self._c;return t("div",{staticClass:"hello"},[t("h1",[e._v(e._s(e.msg))]),e._m(0),t("h3",[e._v("Installed CLI Plugins")]),e._m(1),t("h3",[e._v("Essential Links")]),e._m(2),t("h3",[e._v("Ecosystem")]),e._m(3)])},S=[function(){var e=this,t=e._self._c;return t("p",[e._v(" For a guide and recipes on how to configure / customize this project,"),t("br"),e._v(" check out the "),t("a",{attrs:{href:"https://cli.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("vue-cli documentation")]),e._v(". ")])},function(){var e=this,t=e._self._c;return t("ul",[t("li",[t("a",{attrs:{href:"https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel",target:"_blank",rel:"noopener"}},[e._v("babel")])]),t("li",[t("a",{attrs:{href:"https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint",target:"_blank",rel:"noopener"}},[e._v("eslint")])])])},function(){var e=this,t=e._self._c;return t("ul",[t("li",[t("a",{attrs:{href:"https://vuejs.org",target:"_blank",rel:"noopener"}},[e._v("Core Docs")])]),t("li",[t("a",{attrs:{href:"https://forum.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("Forum")])]),t("li",[t("a",{attrs:{href:"https://chat.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("Community Chat")])]),t("li",[t("a",{attrs:{href:"https://twitter.com/vuejs",target:"_blank",rel:"noopener"}},[e._v("Twitter")])]),t("li",[t("a",{attrs:{href:"https://news.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("News")])])])},function(){var e=this,t=e._self._c;return t("ul",[t("li",[t("a",{attrs:{href:"https://router.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("vue-router")])]),t("li",[t("a",{attrs:{href:"https://vuex.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("vuex")])]),t("li",[t("a",{attrs:{href:"https://github.com/vuejs/vue-devtools#vue-devtools",target:"_blank",rel:"noopener"}},[e._v("vue-devtools")])]),t("li",[t("a",{attrs:{href:"https://vue-loader.vuejs.org",target:"_blank",rel:"noopener"}},[e._v("vue-loader")])]),t("li",[t("a",{attrs:{href:"https://github.com/vuejs/awesome-vue",target:"_blank",rel:"noopener"}},[e._v("awesome-vue")])])])}],_={name:"HelloWorld",props:{msg:String}},N=_,B=(0,c.Z)(N,O,S,!1,null,"b9167eee",null),E=B.exports;r["default"].use(b.Z);const H=[{path:"/kakaologin",name:"KakaoLogin",component:j},{path:"/helloworld",name:"HelloWorld",component:E},{path:"/register",name:"Register",component:()=>n.e(122).then(n.bind(n,6122))}],J=new b.Z({mode:"history",base:"/",routes:H});var P=J,U=n(70),K=n(6882),z=n(6423);n(7024);window.Kakao.init("562fa760eb046b2bf13d97aa8556d908"),r["default"].use(K.ZPm),r["default"].use(z.Z,U.ZP),r["default"].config.productionTip=!1,r["default"].prototype.$axios=U.ZP,new r["default"]({router:P,render:e=>e(g)}).$mount("#app")},2626:function(e){e.exports="data:image/png;base64,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"},2983:function(e,t,n){e.exports=n.p+"img/moim_logo_fullname.85718488.png"}},t={};function n(r){var o=t[r];if(void 0!==o)return o.exports;var a=t[r]={id:r,loaded:!1,exports:{}};return e[r](a,a.exports,n),a.loaded=!0,a.exports}n.m=e,function(){n.amdO={}}(),function(){var e=[];n.O=function(t,r,o,a){if(!r){var i=1/0;for(c=0;c<e.length;c++){r=e[c][0],o=e[c][1],a=e[c][2];for(var s=!0,l=0;l<r.length;l++)(!1&a||i>=a)&&Object.keys(n.O).every((function(e){return n.O[e](r[l])}))?r.splice(l--,1):(s=!1,a<i&&(i=a));if(s){e.splice(c--,1);var u=o();void 0!==u&&(t=u)}}return t}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[r,o,a]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var r in t)n.o(t,r)&&!n.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,r){return n.f[r](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+".0242f497.js"}}(),function(){n.miniCssF=function(e){return"css/"+e+".6135d093.css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.hmd=function(e){return e=Object.create(e),e.children||(e.children=[]),Object.defineProperty(e,"exports",{enumerable:!0,set:function(){throw new Error("ES Modules may not assign module.exports or exports.*, Use ESM export syntax, instead: "+e.id)}}),e}}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="gramvue2:";n.l=function(r,o,a,i){if(e[r])e[r].push(o);else{var s,l;if(void 0!==a)for(var u=document.getElementsByTagName("script"),c=0;c<u.length;c++){var d=u[c];if(d.getAttribute("src")==r||d.getAttribute("data-webpack")==t+a){s=d;break}}s||(l=!0,s=document.createElement("script"),s.charset="utf-8",s.timeout=120,n.nc&&s.setAttribute("nonce",n.nc),s.setAttribute("data-webpack",t+a),s.src=r),e[r]=[o];var h=function(t,n){s.onerror=s.onload=null,clearTimeout(f);var o=e[r];if(delete e[r],s.parentNode&&s.parentNode.removeChild(s),o&&o.forEach((function(e){return e(n)})),t)return t(n)},f=setTimeout(h.bind(null,void 0,{type:"timeout",target:s}),12e4);s.onerror=h.bind(null,s.onerror),s.onload=h.bind(null,s.onload),l&&document.head.appendChild(s)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.p="/"}(),function(){var e=function(e,t,n,r){var o=document.createElement("link");o.rel="stylesheet",o.type="text/css";var a=function(a){if(o.onerror=o.onload=null,"load"===a.type)n();else{var i=a&&("load"===a.type?"missing":a.type),s=a&&a.target&&a.target.href||t,l=new Error("Loading CSS chunk "+e+" failed.\n("+s+")");l.code="CSS_CHUNK_LOAD_FAILED",l.type=i,l.request=s,o.parentNode.removeChild(o),r(l)}};return o.onerror=o.onload=a,o.href=t,document.head.appendChild(o),o},t=function(e,t){for(var n=document.getElementsByTagName("link"),r=0;r<n.length;r++){var o=n[r],a=o.getAttribute("data-href")||o.getAttribute("href");if("stylesheet"===o.rel&&(a===e||a===t))return o}var i=document.getElementsByTagName("style");for(r=0;r<i.length;r++){o=i[r],a=o.getAttribute("data-href");if(a===e||a===t)return o}},r=function(r){return new Promise((function(o,a){var i=n.miniCssF(r),s=n.p+i;if(t(i,s))return o();e(r,s,o,a)}))},o={143:0};n.f.miniCss=function(e,t){var n={122:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=r(e).then((function(){o[e]=0}),(function(t){throw delete o[e],t})))}}(),function(){var e={143:0};n.f.j=function(t,r){var o=n.o(e,t)?e[t]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(n,r){o=e[t]=[n,r]}));r.push(o[2]=a);var i=n.p+n.u(t),s=new Error,l=function(r){if(n.o(e,t)&&(o=e[t],0!==o&&(e[t]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;s.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",s.name="ChunkLoadError",s.type=a,s.request=i,o[1](s)}};n.l(i,l,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,r){var o,a,i=r[0],s=r[1],l=r[2],u=0;if(i.some((function(t){return 0!==e[t]}))){for(o in s)n.o(s,o)&&(n.m[o]=s[o]);if(l)var c=l(n)}for(t&&t(r);u<i.length;u++)a=i[u],n.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return n.O(c)},r=self["webpackChunkgramvue2"]=self["webpackChunkgramvue2"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=n.O(void 0,[998],(function(){return n(6742)}));r=n.O(r)})();
//# sourceMappingURL=app.7c9d1c4a.js.map