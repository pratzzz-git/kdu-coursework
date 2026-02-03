export function addBar(container, price, isPositive) {
  const bar = document.createElement('div');

  bar.style.width = '20px';
  bar.style.height = price + 'px';
  bar.style.backgroundColor = isPositive ? '#2fae44' : '#e03131';
  bar.style.border = '1px solid #000';

  container.appendChild(bar);
}
