// 変数定義（最初に実行される）
const editor = document.getElementsByClassName('simple-wysiwyg')[0];
const toolbar = editor.getElementsByClassName('toolbar')[0];
const buttons = toolbar.querySelectorAll('.editor-btn:not(.has-submenu)');
const contentArea = editor.getElementsByClassName('content-area')[0];
const visuellView = contentArea.getElementsByClassName('visuell-view')[0];
const htmlView = contentArea.getElementsByClassName('html-view')[0];
const modal = document.getElementsByClassName('modal')[0];

// 選択領域の変更時イベントを定義
document.addEventListener('selectionchange', selectionChange);

// ペースト時のイベントを定義
visuellView.addEventListener('paste', pasteEvent);

// キー押下時のイベントを定義
contentArea.addEventListener('keypress', addParagraphTag);

// ツールバーボタンクリックして時イベントを定義
for (let button of buttons) {
  button.addEventListener('click', function (e) {
    let action = this.dataset.action;
    if (action == 'toggle-view') execCodeAction(this, editor);
    if (action == 'createLink') execLinkAction();
    else execDefaultAction(action);
  });
}

/**
 * ヴィシュアルビューとHTMLビューのトグルを行なう
 */
function execCodeAction(button, editor) {
  if (button.classList.contains('active')) { // show visuell view
    visuellView.innerHTML = htmlView.value;
    htmlView.style.display = 'none';
    visuellView.style.display = 'block';

    button.classList.remove('active');
  } else {  // show html view
    htmlView.innerText = visuellView.innerHTML;
    visuellView.style.display = 'none';
    htmlView.style.display = 'block';

    button.classList.add('active');
  }
}

/**
 * 現在選択エリアにリンクを設定する
 */
function execLinkAction() {
  modal.style.display = 'block';
  let selection = saveSelection();

  let submit = modal.querySelectorAll('button.done')[0];
  let close = modal.querySelectorAll('.close')[0];

  // done button active => add link
  submit.addEventListener('click', function (e) {
    e.preventDefault();
    let newTabCheckbox = modal.querySelectorAll('#new-tab')[0];
    let linkInput = modal.querySelectorAll('#linkValue')[0];
    let linkValue = linkInput.value;
    let newTab = newTabCheckbox.checked;

    restoreSelection(selection);

    if (window.getSelection().toString()) {
      let a = document.createElement('a');
      a.href = linkValue;
      if (newTab) a.target = '_blank';
      window.getSelection().getRangeAt(0).surroundContents(a);
    }

    modal.style.display = 'none';
    linkInput.value = '';

    // deregister modal events
    submit.removeEventListener('click', arguments.callee);
    close.removeEventListener('click', arguments.callee);
  });

  // モーダルダイアログのX(閉じる)ボタンクリック時
  close.addEventListener('click', function (e) {
    e.preventDefault();
    let linkInput = modal.querySelectorAll('#linkValue')[0];

    modal.style.display = 'none';
    linkInput.value = '';

    // deregister modal events
    submit.removeEventListener('click', arguments.callee);
    close.removeEventListener('click', arguments.callee);
  });
}

/**
 * この関数はノーマルイベントを処理する
 */
function execDefaultAction(action) {
  document.execCommand(action, false);
}

/**
 * 現在の選択範囲を保存する
 */
function saveSelection() {
  if (window.getSelection) {
    sel = window.getSelection();
    if (sel.getRangeAt && sel.rangeCount) {
      let ranges = [];
      for (var i = 0, len = sel.rangeCount; i < len; ++i) ranges.push(sel.getRangeAt(i));

      return ranges;
    }
  } else if (document.selection && document.selection.createRange) return document.selection.createRange();

  return null;
}

/**
 *  保存した選択範囲をロードする
 */
function restoreSelection(savedSel) {
  if (savedSel) {
    if (window.getSelection) {
      sel = window.getSelection();
      sel.removeAllRanges();
      for (var i = 0, len = savedSel.length; i < len; ++i) sel.addRange(savedSel[i]);

    } else if (document.selection && savedSel.select) savedSel.select();
  }
}

/**
 * 現在の選択範囲をアクティブ・インアクティブに変更する
 */
function selectionChange(e) {
  for (let button of buttons) {

    // don't remove active class on code toggle button
    if (button.dataset.action === 'toggle-view') continue;

    button.classList.remove('active');
  }

  if (!childOf(window.getSelection().anchorNode.parentNode, editor)) return false;

  parentTagActive(window.getSelection().anchorNode.parentNode);
}

/**
 * 渡された子の親をチェックする
 */
function childOf(child, parent) {
  return parent.contains(child);
}

/**
 * 現在の選択範囲にわたされたタグが設定可能かチェックする
 */
function parentTagActive(elem) {
  if (!elem || !elem.classList || elem.classList.contains('visuell-view')) return false;

  let toolbarButton;

  // active by tag names
  let tagName = elem.tagName.toLowerCase();
  toolbarButton = document.querySelectorAll(`.toolbar .editor-btn[data-tag-name="${tagName}"]`)[0];
  if (toolbarButton) toolbarButton.classList.add('active');

  // active by text-align
  let textAlign = elem.style.textAlign;
  toolbarButton = document.querySelectorAll(`.toolbar .editor-btn[data-style="textAlign:${textAlign}"]`)[0];
  if (toolbarButton) toolbarButton.classList.add('active');

  return parentTagActive(elem.parentNode);
}

/**
 * ペーストされたものをチェックしてHTMLを除去する
 */
function pasteEvent(e) {
  e.preventDefault();

  let text = (e.originalEvent || e).clipboardData.getData('text/plain');
  document.execCommand('insertHTML', false, text);
}

/**
 * エンターキー押下時にpタグを追加する
 */
function addParagraphTag(e) {
  if (e.keyCode == '13') {

    // don't add a p tag on list item
    if (window.getSelection().anchorNode.parentNode.tagName === 'LI') return;
    document.execCommand('formatBlock', false, 'p');
  }
}