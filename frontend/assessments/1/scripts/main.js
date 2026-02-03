import { generatePrice } from './stock.js';
import { addBar } from './graph.js';
import { addHistory } from './history.js';

document.addEventListener('DOMContentLoaded', () => {
  const barsContainer = document.querySelector('.bars');
  const priceValue = document.querySelector('.price-value');
  const priceChange = document.querySelector('.price-change');

  const qtyInput = document.querySelector('input');
  const buyBtn = document.querySelector('.btn-buy');
  const sellBtn = document.querySelector('.btn-sell');
  const historyList = document.querySelector('.history-list');

  function updatePrice() {
    const { price, isPositive } = generatePrice();

    priceValue.textContent = price;
    priceChange.textContent = isPositive ? '▲' : '▼';
    priceChange.style.color = isPositive ? '#2fae44' : '#e03131';

    addBar(barsContainer, price, isPositive);
  }

  setInterval(updatePrice, 5000);

  buyBtn.addEventListener('click', () => {
    const qty = qtyInput.value;

    if (!qty) return;

    addHistory(historyList, qty, 'Buy');
    qtyInput.value = '';
  });

  
  sellBtn.addEventListener('click', () => {
    const qty = qtyInput.value;

    if (!qty) return;

    addHistory(historyList, qty, 'Sell');
    qtyInput.value = '';
  });
});
