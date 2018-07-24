<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>彩信折扣券系统</title>
    <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="ext/ux/uxcss.css"/>
    <link rel="stylesheet" type="text/css" href="ext/ux/statusbar.css"/>
    <STYLE type="text/css">
        .x-tree-node-el {
            border-bottom-width: 0px;
            height: 16px;
        }

        .x-tree-col {
            border-right-width: 0px;
            height: 22px;
        }

        .x-tree-selected {
            border-color: #d0def0;
            height: 16px;
        }
    </STYLE>
    <script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="ext/ext-all-debug.js"></script>
    <script type="text/javascript" src="ext/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="ext/ux/statusbar.js"></script>
    <script type="text/javascript" src="ext/ux/uxjs.js"></script>
    <%@ include file="/commPage.jsp" %>
    <script type="text/javascript">


        function MM_swapImgRestore() {
            var i, x, a = document.MM_sr;
            for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++) x.src = x.oSrc;
        }


        function MM_preloadImages() {
            var d = document;
            if (d.images) {
                if (!d.MM_p) d.MM_p = new Array();
                var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
                for (i = 0; i < a.length; i++)
                    if (a[i].indexOf("#") != 0) {
                        d.MM_p[j] = new Image;
                        d.MM_p[j++].src = a[i];
                    }
            }
        }

        function MM_findObj(n, d) {
            var p, i, x;
            if (!d) d = document;
            if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
                d = parent.frames[n.substring(p + 1)].document;
                n = n.substring(0, p);
            }
            if (!(x = d[n]) && d.all) x = d.all[n];
            for (i = 0; !x && i < d.forms.length; i++) x = d.forms[i][n];
            for (i = 0; !x && d.layers && i < d.layers.length; i++) x = MM_findObj(n, d.layers[i].document);
            return x;
        }

        function MM_swapImage() {
            var i, j = 0, x, a = MM_swapImage.arguments;
            document.MM_sr = new Array;
            for (i = 0; i < (a.length - 2); i += 3)
                if ((x = MM_findObj(a[i])) != null) {
                    document.MM_sr[j++] = x;
                    if (!x.oSrc) x.oSrc = x.src;
                    x.src = a[i + 2];
                }
        }

        function delfavorite(e) {
            var li = Ext.get(e).up("li");
            var id_key = li.dom.id.replace("_fav_li", "");
            Ext.Ajax.request({
                url: 'services.do?method=delfavorite',
                params: {node: id_key},
                success: function () {
                    shouchang.getLoader().load(shouchang.getRootNode());
                }
            });
            li.remove();
        }

        Ext.onReady(function () {
            Ext.BLANK_IMAGE_URL = "ext/resources/images/default/s.gif";
            Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
            Ext.QuickTips.init();
            Ext.form.Field.prototype.msgTarget = 'side';

            function failfunc() {
                Ext.Msg.minWidth = 300;
                Ext.Msg.alert('错误信息', '数据库连接失败，请重新登陆！');
            }

            /*mainmenu*/
            var menu = new Ext.tree.TreePanel({
                bodyStyle: 'background-image:url("ext/resources/icons/bg1.jpg")',
                region: 'center',
                autoScroll: true,
                split: true,
                width: 225,
                minSize: 175,
                border: false,
                maxSize: 400,
                stateId: 'menu',
                statefull: true,
                rootVisible: false,
                enableDrag: true,
                ddGroup: 'dd',
                collapseFirst: false,
                root: new Ext.tree.AsyncTreeNode({
                    id: "0",
                    text: "root",
                    leaf: false,
                    expanded: true
                }),
                loader: new Ext.tree.TreeLoader({
                    dataUrl: "sysFunctionAction!findMenu.action"
                })
            });
            shouchang = new Ext.tree.TreePanel({
                bodyStyle: 'background-image:url("ext/resources/icons/bg3.png")',
                autoScroll: true,
                split: true,
                width: 225,
                region: 'south',
                height: 200,
                minSize: 175,
                maxSize: 400,
                border: false,
                stateId: 'shouchang',
                statefull: true,
                rootVisible: false,
                title: '我的收藏夹',
                collapseFirst: false,
                collapsible: true,
                root: new Ext.tree.AsyncTreeNode({
                    id: "0",
                    text: "root",
                    leaf: false,
                    expanded: true
                }),
                loader: new Ext.tree.TreeLoader({
                    dataUrl: "services.do?method=getfavorite"
                })
            });

            function add(node) {
                if (!node.attributes) node = this;
                var root = shouchang.getRootNode();
                var flag = true;
                root.eachChild(function (n) {
                    if (n.attributes.id == node.id) {
                        flag = false;
                        return false;
                    }
                });
                if (!flag) return;

                var newNode = new Ext.tree.TreeNode({
                    id: node.id,
                    text: node.text,
                    functionUrl: node.attributes.functionUrl,
                    leaf: true
                });
                root.appendChild(newNode);
                var data = [], obj = {};
                obj.favirete = '';
                obj.functionid = node.id;
                data.push(obj);
                Ext.Ajax.request({
                    url: 'loginAction!addfavorite.action',
                    params: {
                        json: Ext.util.JSON.encode(data)
                    },
                    failure: failfunc
                });
            }

            function onclick(n, e) {
                if (n.isLeaf()) {
                    if (n.attributes.functionUrl.trim() == "") {
                        mainpanel.setTitle(n.text + "(该菜单对应功能正在建设中！)");
                        iframe.loginTo("jsp/index.jsp");
                        return;
                    }
                    mainpanel.setTitle(n.text);
                    iframe.loginTo(n.attributes.functionUrl);
                }
            }

            menu.on("click", onclick);
            menu.on("contextmenu", function (node, e) {
                if (node.isLeaf()) {
                    var menu = new Ext.menu.Menu([{
                        text: '添加到收藏夹',
                        scope: node,
                        handler: add
                    }]);
                    menu.showAt(e.getXY());
                }
            });
            menu.on('dragdrop', function (tree, node, dd, e) {
                add(node);
            });

            function menudel() {
                if (this.id != '') {
                    Ext.Ajax.request({
                        url: 'services.do?method=delfavorite',
                        params: {
                            id_key: this.id
                        },
                        failure: failfunc
                    });
                    this.remove();
                }
            }

            shouchang.on("contextmenu", function (node, e) {
                var menu1 = new Ext.menu.Menu([{
                    text: '删除',
                    scope: node,
                    handler: menudel
                }]);
                menu1.showAt(e.getXY());
            });
            shouchang.on("click", onclick);
            /*statusbar*/
            var sb = new Ext.ux.StatusBar({
                region: 'south',
                defaultText: '当前用户:<s:property value="sysUser.userName"/>',
                statusAlign: 'left',
                items: ['-', '广西南宁', '-', '']
            })
            /*mainpanel*/
            Ext.ux.IFrame = Ext.extend(Ext.BoxComponent, {
                onRender: function (ct, position) {
                    this.el = ct.createChild({tag: 'iframe', id: this.id, frameBorder: 0, src: this.url});
                },
                loginTo: function (src) {
                    if (src.indexOf('?') != -1)
                        src += '&timeStamp=' + new Date().getTime();
                    else
                        src += '?timeStamp=' + new Date().getTime();
                    this.el.dom.src = src;
                }
            })
            var defaulturl = 'jsp/index.jsp';
            var defaulttitle = '<s:property value="sysFunction.functionname"/>';
            if (defaulttitle == '') {
                defaulttitle = '系统首页';
            }
            var iframe = new Ext.ux.IFrame({id: 'mainframe', url: defaulturl});
            var mainpanel = new Ext.Panel({
                id: 'passmainpanel',
                title: defaulttitle,
                split: true,
                region: 'center',
                layout: 'fit',
                items: [iframe],
                bodyStyle: 'background-image:url("ext/resources/icons/bg0.jpg")'
            });
            var viewport = new Ext.Viewport({
                layout: 'border',
                items: [
                    {
                        xtype: 'panel',
                        region: 'north',
                        height: 60,
                        contentEl: 'north',
                        bodyStyle: 'background-image:url("ext/resources/icons/apptop.jpg")'
                    },
                    {
                        xtype: 'panel',
                        width: 225,
                        title: '菜单工具栏',
                        region: 'west',
                        split: true,
                        layout: 'border',
                        stateId: 'westpanel',
                        statefull: true,
                        collapsible: true,
                        layoutConfig: {align: "stretch", pack: "start"},
                        items: [menu, shouchang]
                    },
                    mainpanel,
                    sb
                ]
            });
            new Ext.util.DelayedTask().delay(200, function () {
                new Ext.dd.DDTarget(shouchang.getId(), 'dd');
                menu.body.on("contextmenu", function (e) {
                    e.stopEvent();
                });
                shouchang.body.on("contextmenu", function (e) {
                    e.stopEvent();
                });
                shouchang.setHeight(200);
                iframe.el.on("contextmenu", function (e) {
                    e.stopEvent();
                });
                //sb.showBusy("ddddddddddddddd");
            });

        });


        function loginOut() {

            Ext.Ajax.request({
                url: 'loginAction!quit.action',
                params: {
                    json: Ext.util.JSON.encode('')
                },
                failure: jump,
                success: jump
            });

        }

        function jump() {
            window.location.href = './';
        }
    </script>
</head>
<body>
<div id="north">
    <input type="hidden" id="mainJspPage"><!--用于判断框架位置不能修改 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="100%" style="background-image: url('images/logo.jpg')"></td>
            <td width="73"><a href="javaScript:" onMouseOut="MM_swapImgRestore()"
                              onMouseOver="MM_swapImage('quit','','ext/resources/icons/quit_01.jpg',1)"
                              onclick="loginOut();"><img name="quit" border="0" src="ext/resources/icons/quit.jpg"></a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>