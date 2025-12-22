<script>
    let {
      open = false,
      symbol = "",
      onConfirm,
      onClose
    } = $props();
  
    let quantity = $state(0);
    let price = $state(0);
  
    function confirm() {
      if (!quantity || !price) return;
      onConfirm(symbol, quantity, price);
      quantity = 0;
      price = 0;
      onClose();
    }
  </script>
  
  {#if open}
    <dialog open class="modal">
      <h3>Sell {symbol}</h3>
  
      <input
        type="number"
        placeholder="Quantity"
        bind:value={quantity}
      />
  
      <input
        type="number"
        placeholder="Sell price"
        bind:value={price}
      />
  
      <div class="actions">
        <button type="button" onclick={confirm}>Sell</button>
        <form method="dialog">
          <button type="submit" class="secondary">Cancel</button>
        </form>
      </div>
    </dialog>
  {/if}


  <style>
    dialog.modal {
      border: none;
      border-radius: 8px;
      padding: 1.5rem;
      width: 300px;
    }
  
    dialog::backdrop {
      background: rgba(0, 0, 0, 0.4);
    }
  
    .actions {
      display: flex;
      justify-content: flex-end;
      gap: 0.5rem;
    }
  
    .secondary {
      background: #eee;
    }
  </style>
  
  