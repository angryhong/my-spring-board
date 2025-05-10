// static/js/market_best.js
document.addEventListener('DOMContentLoaded', () => {

  // 1) 필터 변경 시 자동 제출
  const filterForm = document.querySelector('#filter-form');
  if (filterForm) {
    filterForm.querySelectorAll('select').forEach(sel => {
      sel.addEventListener('change', () => {
        filterForm.submit();
      });
    });
  }

  // 2) 무한 스크롤 로직
  let page = parseInt(new URLSearchParams(window.location.search).get('page') || 1);
  const grid = document.querySelector('.row-cols-2');
  let loading = false;

  const loadMore = () => {
    if (loading) return;
    loading = true;
    page++;
    const params = new URLSearchParams(window.location.search);
    params.set('page', page);

    // 스켈레톤 UI 추가
    const skeletons = [];
    for (let i = 0; i < 4; i++) {
      const col = document.createElement('div');
      col.className = 'col';
      col.innerHTML = `
        <div class="card skeleton">
          <div class="skeleton img"></div>
          <div class="card-body">
            <div class="skeleton text" style="width:80%"></div>
            <div class="skeleton text" style="width:50%"></div>
          </div>
        </div>`;
      grid.appendChild(col);
      skeletons.push(col);
    }

    fetch(`${window.location.pathname}?${params.toString()}`, {
      headers: { 'X-Requested-With': 'XMLHttpRequest' }
    })
    .then(res => res.text())
    .then(html => {
      // 응답에서 카드만 파싱해 추가
      const tmp = document.createElement('div');
      tmp.innerHTML = html;
      const newCards = tmp.querySelectorAll('.row-cols-2 .col');
      newCards.forEach(c => grid.appendChild(c));
    })
    .catch(console.error)
    .finally(() => {
      // 스켈레톤 제거
      skeletons.forEach(s => s.remove());
      loading = false;
    });
  };

  // 스크롤 이벤트: 바닥 근처 도달하면 loadMore 호출
  window.addEventListener('scroll', () => {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 200) {
      loadMore();
    }
  });
});
