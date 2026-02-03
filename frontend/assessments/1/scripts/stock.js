let previousPrice = 142;

export function getInitialPrice() {
  return previousPrice;
}

export function generatePrice() {
  const newPrice = Math.floor(Math.random() * 501);
  const isPositive = newPrice >= previousPrice;

  previousPrice = newPrice;

  return { price: newPrice, isPositive };
}
