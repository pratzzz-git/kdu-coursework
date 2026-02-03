export function addHistory(container, qty, type) {
  const item = document.createElement('div');
  item.className = 'history-item';

  const header = document.createElement('div');
  header.className = 'history-header';

  const qtyText = document.createElement('span');
  qtyText.textContent = `${qty} stocks`;

  const typeText = document.createElement('span');
  typeText.textContent = type;

  typeText.style.color = type === 'Buy' ? '#2fae44' : '#e03131';

  header.appendChild(qtyText);
  header.appendChild(typeText);

  const time = document.createElement('div');
  time.className = 'history-time';
  time.textContent = new Date().toUTCString();

  item.appendChild(header);
  item.appendChild(time);

  container.prepend(item);
}
